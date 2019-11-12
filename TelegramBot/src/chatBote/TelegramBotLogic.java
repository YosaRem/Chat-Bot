package chatBote;

import game.Player;
import game.QuizGame;
import game.QuizLogic;
import publisher_subscriber.ISubscriber;
import tasks_extractor.QuizTasksExtractor;
import writers.IWriter;
import writers.TelegramWriter;

import java.util.HashMap;

public class TelegramBotLogic implements ISubscriber{
    private QuizTasksExtractor extractor;
    private final HashMap<String, ISubscriber> subscribers;
    private TelegramBot telegramBot;

    public TelegramBotLogic(TelegramBot telegramBot, QuizTasksExtractor extractor) {
        this.telegramBot = telegramBot;
        subscribers = new HashMap<>();
        this.extractor = extractor;
    }

    private ISubscriber createGame(String chatId, String firstName) {
        IWriter writer = new TelegramWriter(telegramBot, chatId);
        QuizGame game = new QuizGame(extractor);
        Player player = new Player(firstName);
        QuizLogic quizLogic = new QuizLogic(writer, player, game, "resources/help.txt");
        quizLogic.startGame();
        return quizLogic;
    }

    @Override
    public void objectModified(String data) {
        String[] datas = data.split("|");
        String text = datas[0];
        String chatId = datas[1];
        String firstName = datas[2];
        synchronized (subscribers) {
            ISubscriber currentSubscriber = subscribers.get(chatId);
            if (currentSubscriber != null) {
                currentSubscriber.objectModified(text);
            } else {
                ISubscriber game = createGame(chatId, firstName);
                subscribers.put(chatId, game);
            }
        }
    }
}
