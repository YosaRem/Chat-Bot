package chatBot;

import chatBot.keyboards.StandardKeyboard;
import commands.CommandConverter;
import game.Player;
import game.QuizGame;
import game.QuizLogic;
import publisher_subscriber.ISubscriber;
import tasks_extractor.QuizTasksExtractor;
import writers.ITelegramWriterFactory;
import writers.IWriter;
import writers.TelegramWriter;
import writers.WriterBuilder;

import java.util.*;

public class TelegramBotLogic implements ISubscriber<TelegramMesData> {
    private QuizTasksExtractor extractor;
    private final HashMap<UserData, QuizLogic> subscribers;
    private ITelegramBot telegramBot;
    private ITelegramWriterFactory writerFactory;

    public TelegramBotLogic(ITelegramBot telegramBot, QuizTasksExtractor extractor, ITelegramWriterFactory writerFactory) {
        this.telegramBot = telegramBot;
        this.subscribers = new HashMap<>();
        this.extractor = extractor;
        this.writerFactory = writerFactory.setBot(telegramBot);
    }

    private QuizLogic createGame(UserData user) {
        IWriter writer = writerFactory.setMsgKeyboard(new StandardKeyboard()).compile(user.getChatId());
        QuizGame game = new QuizGame(extractor);
        Player player = new Player(user.getName());
        QuizLogic quizLogic = new QuizLogic(writer, player, game);
        quizLogic.startGame();
        return quizLogic;
    }

    @Override
    public void objectModified(TelegramMesData data) {
        synchronized (subscribers) {
            QuizLogic currentSubscriber = subscribers.get(data.getUser());
            if (currentSubscriber == null) {
                QuizLogic game = createGame(data.getUser());
                subscribers.put(data.getUser(), game);
            }
            CommandConverter cc = new CommandConverter(subscribers.get(data.getUser()), subscribers);
            cc.defineCommands();
            if (cc.canConvert(data.getText())) {
                cc.getCommand(data.getText()).justDoIt(data, writerFactory);
                writerFactory.setMsgKeyboard(new StandardKeyboard());
            } else {
                currentSubscriber.objectModified(data.getText());
            }
        }
    }
}
