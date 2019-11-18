package chatBote;

import game.Player;
import game.QuizGame;
import game.QuizLogic;
import publisher_subscriber.ISubscriber;
import tasks_extractor.QuizTasksExtractor;
import writers.IWriter;
import writers.TelegramWriter;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class TelegramBotLogic implements ISubscriber<TelegramMesData> {
    private QuizTasksExtractor extractor;
    private TelegramBot telegramBot;
    public final HashMap<String, ISubscriber> subscribers;

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

    public Set<String> getGamesID() {
        return subscribers.keySet();
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
