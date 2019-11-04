package chatBote;

import game.Player;
import game.QuizGame;
import game.QuizLogic;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.io.IOException;
import java.util.*;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import publisher_subscriber.IPublisher;
import publisher_subscriber.ISubscriber;
import readers.IReader;
import tasks_extractor.QuizTasksExtractor;
import writers.IWriter;
import writers.TelegramWriter;


public class TelegramBot extends TelegramLongPollingBot implements IReader, IPublisher {
    private ISubscriber subscriber;
    private QuizTasksExtractor extractor;
    private HashMap<String, ISubscriber> Subscribers;

    public TelegramBot(QuizTasksExtractor extractor) {
        this.extractor = extractor;
        Subscribers = new HashMap<>();
    }

    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        String chatId = update.getMessage().getChatId().toString();
        String firstName = update.getMessage().getChat().getFirstName();
        printToConsole(update, chatId, firstName, message);
        ISubscriber currentSubscriber = Subscribers.get(chatId);
        if (currentSubscriber != null) {
            if (currentSubscriber.isSubscriberReady()) {
                currentSubscriber.objectModified(message);
                /*Runnable r = () -> {

                };
                Thread th = new Thread(r);
                th.start();*/
            }
        } else {
            ISubscriber game = createGame(chatId, firstName);
            Subscribers.put(chatId, game);
        }
    }

    private ISubscriber createGame(String chatId, String firstName) {
        IWriter writer = new TelegramWriter(this, chatId);
        QuizGame game = new QuizGame(extractor);
        Player player = new Player(firstName);
        QuizLogic quizLogic = new QuizLogic(writer, player, game, "resources/help.txt");
        quizLogic.startGame();
        return quizLogic;
    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMes = new SendMessage();
        System.out.println(s);
        sendMes.enableMarkdown(true);
        sendMes.setChatId(chatId);
        sendMes.setText(s);
        setButtons(sendMes);
        try {
            execute(sendMes);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void printToConsole(Update update, String chatId, String firstName, String message) {
        System.out.println(update.getMessage().getDate() + " " + chatId);
        System.out.println(firstName + ": " + message);
        System.out.println(Subscribers.size());
    }

    private synchronized void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardARow = new KeyboardRow();
        KeyboardRow keyboardBRow = new KeyboardRow();
        KeyboardRow keyboardCRow = new KeyboardRow();
        KeyboardRow keyboardDRow = new KeyboardRow();
        keyboardARow.add(new KeyboardButton("1"));
        keyboardBRow.add(new KeyboardButton("2"));
        keyboardCRow.add(new KeyboardButton("3"));
        keyboardDRow.add(new KeyboardButton("4"));
        keyboard.add(keyboardARow);
        keyboard.add(keyboardBRow);
        keyboard.add(keyboardCRow);
        keyboard.add(keyboardDRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    @Override
    public String getBotUsername() {
        return "Millionare_chat_bot";
    }

    @Override
    public String getBotToken() {
        return "1055331641:AAHr8zihVZw7gWFvwNObGjVBAEiQ-cwkeiY";
    }

    @Override
    public String read() throws IOException {
        return null;
    }

    @Override
    public void subscribe(ISubscriber subscriber) {
        this.subscriber = subscriber;
    }
}
