package chatBote;

import game.Player;
import game.QuizGame;
import game.QuizLogic;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.*;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import publisher_subscriber.ISubscriber;
import tasks_extractor.QuizTasksExtractor;
import writers.IWriter;
import writers.TelegramWriter;


public class TelegramBot extends TelegramLongPollingBot {
    private QuizTasksExtractor extractor;
    private final HashMap<String, ISubscriber> subscribers;
    private final String botName = "Millionare_chat_bot";
    private final String token = "1055331641:AAHr8zihVZw7gWFvwNObGjVBAEiQ-cwkeiY";

    public TelegramBot(QuizTasksExtractor extractor) {
        this.extractor = extractor;
        subscribers = new HashMap<>();
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            String text = message.getText();
            String chatId = message.getChatId().toString();
            String firstName = message.getChat().getFirstName();
            printToConsole(message.getDate(), chatId, firstName, text);
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

    private void printToConsole(Integer date, String chatId, String firstName, String message) {
        System.out.println("////////////////////////////////////////////////");
        System.out.println(date + " id: " + chatId);
        System.out.println("Player: " + firstName + ": " + message);
        System.out.println("Amount of games: " + subscribers.size());
    }

    private synchronized void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("1"));
        keyboardFirstRow.add(new KeyboardButton("2"));
        keyboardSecondRow.add(new KeyboardButton("3"));
        keyboardSecondRow.add(new KeyboardButton("4"));
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
