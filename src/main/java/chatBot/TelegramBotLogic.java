package chatBot;

import commands.HintCommand;
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
    private final HashMap<String, QuizLogic> subscribers;
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
        QuizLogic quizLogic = new QuizLogic(writer, player, game, "src/main/resources/help.txt");
        quizLogic.startGame();
        return quizLogic;
    }


    @Override
    public void objectModified(TelegramMesData data) {
        synchronized (subscribers) {
            QuizLogic currentSubscriber = subscribers.get(data.getChatId());
            if (currentSubscriber != null) {
                if (data.getText().equals("/resend")) {
                    getFriends(subscribers.get(data.getChatId()), data.getChatId());
                    return;
                }
                currentSubscriber.objectModified(data.getText());
            } else if (joined.contains(data.getChatId())) {
                if (data.getText().equals("Начать")) {
                    QuizLogic game = createGame(data.getChatId(), data.getName());
                    subscribers.put(data.getChatId(), game);
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

    public void getFriends(QuizLogic logic, String id) {
        Random rnd = new Random();
        int userCount = 2;
        System.out.println("Subscribers=" + subscribers.size());
        if (subscribers.size() == 1) {
            System.out.println("Was return");
            return;
        }

        ArrayList<String> otherUsers = new ArrayList<>();
        for (String subscriber : subscribers.keySet()) {
            if (subscriber.equals(id))
                continue;
            otherUsers.add(subscriber);
        }
        Collections.shuffle(otherUsers);
        System.out.println("Confirm other" + otherUsers.size());
        int number = 0;
        if (otherUsers.size() > userCount) {
            number = rnd.nextInt(otherUsers.size() - userCount);
        }
        ArrayList<UserData> friends = new ArrayList<>();
        System.out.println("Other users=" + otherUsers.size());

        for (int i = number; i < otherUsers.size() && i < number + userCount; i++) {
            String tmpId = otherUsers.get(i);
            friends.add(new UserData(subscribers.get(tmpId).getPlayer().getName(), otherUsers.get(i)));
        }
        System.out.println("Subscribers=" + subscribers.size());
        TelegramWriter writer = (TelegramWriter) logic.getWriter();
        writer.setKeyboard(new UsersKeyboard(friends));
        writer.printMsg("Пользователи");
    }
}
