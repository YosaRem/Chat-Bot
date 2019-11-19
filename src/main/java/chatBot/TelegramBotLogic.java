package chatBot;

import game.Player;
import game.QuizGame;
import game.QuizLogic;
import publisher_subscriber.ISubscriber;
import tasks_extractor.QuizTasksExtractor;
import writers.IWriter;
import writers.TelegramWriter;

import java.util.HashMap;

public class TelegramBotLogic implements ISubscriber<TelegramMesData> {
    private QuizTasksExtractor extractor;
    private final HashMap<String, ISubscriber> subscribers;
    private ITelegramBot telegramBot;

    public TelegramBotLogic(ITelegramBot telegramBot, QuizTasksExtractor extractor) {
        this.telegramBot = telegramBot;
        subscribers = new HashMap<>();
        this.extractor = extractor;
    }

    private ISubscriber createGame(String chatId, String firstName) {
        IWriter writer = new TelegramWriter(telegramBot, chatId);
        QuizGame game = new QuizGame(extractor);
        Player player = new Player(firstName);
        QuizLogic quizLogic = new QuizLogic(writer, player, game, "src/main/resources/help.txt");
        quizLogic.startGame();
        return quizLogic;
    }


    @Override
    public void objectModified(TelegramMesData data) {
        synchronized (subscribers) {
            ISubscriber currentSubscriber = subscribers.get(data.chatId);
            if (currentSubscriber != null) {
                currentSubscriber.objectModified(data.text);
            } else {
                ISubscriber game = createGame(data.chatId, data.name);
                subscribers.put(data.chatId, game);
            }
        }
    }
}
