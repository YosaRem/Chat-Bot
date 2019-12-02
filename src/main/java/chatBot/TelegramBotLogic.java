package chatBot;

import commands.CommandConverter;
import commands.CommandData;
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
                if (CommandConverter.canConvert(data.getText())){
                    CommandConverter
                            .getCommand(data.getText())
                            .justDoIt(new CommandData(
                                    currentSubscriber,
                                    this,
                                    data.getChatId(),
                                    data.getText())
                            );
                }
                if (data.getText().equals("/resend")) {
                    //getFriends(subscribers.get(data.getChatId()), data.getChatId());
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

    public HashMap<String, QuizLogic> getSubscribers() {
        return new HashMap<String, QuizLogic>(subscribers);
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
}
