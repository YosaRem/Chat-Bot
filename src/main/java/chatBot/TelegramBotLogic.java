package chatBot;

import game.Player;
import game.QuizGame;
import game.QuizLogic;
import publisher_subscriber.ISubscriber;
import taks_models.QuizTask;
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
                } else {
                    String[] words = data.getText().split("_");
                    if (words[0].equals("/resendrequest")) {
                        sendHelpTask(data.getChatId(), words[1]);
                        return;
                    } else if (words[0].equals("/resendanswer")) {
                        sendHelpAnswer(words[2], words[1]);
                        return;
                    }
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

    public void sendHelpTask(String from, String to) {
        QuizLogic logicTo = subscribers.get(to);
        QuizLogic logicFrom = subscribers.get(from);
        TelegramWriter writer = (TelegramWriter) logicTo.getWriter();
        QuizTask task = logicFrom.getCurrentTask();
        writer.printMsg("Игрок " + logicFrom.getPlayer().getName() + " просит помочь с задачей");
        writer.setKeyboard(new RequestAnswerKeyboard(new ArrayList<>(task.getOptions().values()), from));
        writer.printMsg(task.getQuestion());
    }

    public void sendHelpAnswer(String text, String to) {
        QuizLogic logicTo = subscribers.get(to);
        TelegramWriter writer = (TelegramWriter) logicTo.getWriter();
        writer.printMsg("Вам советуют ответить: " + text);
    }

    public void getFriends(QuizLogic logic, String id) {
        int userCount = 2;
        if (subscribers.size() == 1) {
            logic.getWriter().printMsg("В данный момент нет других пользователей.");
            return;
        }

        ArrayList<String> otherUsers = new ArrayList<>();
        for (String subscriber : subscribers.keySet()) {
            if (subscriber.equals(id))
                continue;
            otherUsers.add(subscriber);
        }
        Collections.shuffle(otherUsers);

        int number = 0;
        Random rnd = new Random();
        if (otherUsers.size() > userCount) {
            number = rnd.nextInt(otherUsers.size() - userCount);
        }

        ArrayList<UserData> friends = new ArrayList<>();
        for (int i = number; i < otherUsers.size() && i < number + userCount; i++) {
            String tmpId = otherUsers.get(i);
            friends.add(new UserData(subscribers.get(tmpId).getPlayer().getName(), tmpId));
        }
        TelegramWriter writer = (TelegramWriter) logic.getWriter();
        writer.setKeyboard(new RequestKeyboard(friends));
        writer.printMsg("Пользователи");
    }
}
