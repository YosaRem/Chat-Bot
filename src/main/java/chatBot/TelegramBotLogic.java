package chatBot;

import chatBot.keyboards.StandardKeyboard;
import chatBot.keyboards.StartKeyboard;
import commands.CommandConverter;
import game.Player;
import game.QuizGame;
import game.QuizLogic;
import publisher_subscriber.ISubscriber;
import tasks_extractor.QuizTasksExtractor;
import writers.IWriter;
import writers.TelegramWriter;

import java.util.*;

public class TelegramBotLogic implements ISubscriber<TelegramMesData> {
    private QuizTasksExtractor extractor;
    private final HashMap<UserData, QuizLogic> subscribers;
    private final HashSet<String> joined;
    private ITelegramBot telegramBot;

    public TelegramBotLogic(ITelegramBot telegramBot, QuizTasksExtractor extractor) {
        this.telegramBot = telegramBot;
        this.subscribers = new HashMap<>();
        this.joined = new HashSet<>();
        this.extractor = extractor;
    }

    private QuizLogic createGame(String chatId, String firstName) {
        IWriter writer = new TelegramWriter(telegramBot, chatId, new StandardKeyboard());
        QuizGame game = new QuizGame(extractor);
        Player player = new Player(firstName);
        QuizLogic quizLogic = new QuizLogic(writer, player, game);
        quizLogic.startGame();
        return quizLogic;
    }

    @Override
    public void objectModified(TelegramMesData data) {
        synchronized (subscribers) {
            QuizLogic currentSubscriber = subscribers.get(data.getUser());
            if (currentSubscriber != null) {
                CommandConverter cc = new CommandConverter(subscribers.get(data.getUser()), subscribers);
                cc.defineCommands();
                if (cc.canConvert(data.getText())) {
                    cc.getCommand(data.getText()).justDoIt(data);
                } else {
                    currentSubscriber.objectModified(data.getText());
                }
            } else if (joined.contains(data.getChatId())) {
                if (data.getText().equals("Начать")) {
                    QuizLogic game = createGame(data.getChatId(), data.getName());
                    subscribers.put(data.getUser(), game);
                } else {
                    telegramBot.sendMsg(data.getChatId(), "Чтобы возобновить общение пошлите любое сообщение", new StartKeyboard());
                }
                joined.remove(data.getChatId());
            } else {
                telegramBot.sendMsg(data.getChatId(), "Хотите начать игру?", new StartKeyboard());
                joined.add(data.getChatId());
            }
        }
    }
}
