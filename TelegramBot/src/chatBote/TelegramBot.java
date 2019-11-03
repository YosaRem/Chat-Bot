package chatBote;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import publisher_subscriber.IPublisher;
import publisher_subscriber.ISubscriber;
import readers.IReader;

//import static java.awt.DefaultKeyboardFocusManager.sendMessage;

public class TelegramBot extends TelegramLongPollingBot implements IReader, IPublisher {
    private ISubscriber subscriber;


    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        System.out.println(update.getMessage().getDate() + " " + update.getMessage().getChatId().toString());
        System.out.println(update.getMessage().getChat().getFirstName() + ": " + message);
        /*if (update.getMessage().getChatId() == 531962827) {
            sendMsg(update.getMessage().getChatId().toString(), "Бот доступен только для админа \nОн все  видит");
            return;
        }
        if (update.getMessage().getChatId() == 665600203) {
            sendMsg(update.getMessage().getChatId().toString(), "Временно недоступен");
        } else if (message.equals("/start")) {
            sendMsg(update.getMessage().getChatId().toString(), "Hello, " + update.getMessage().getChat().getFirstName());
        } else sendMsg(update.getMessage().getChatId().toString(), message);*/
        if (update.getMessage().getChatId() == 665600204) {
            sendMsg(update.getMessage().getChatId().toString(), "ИДЗ не скоро, коллоквиум скоро");
            return;
        }
        if (subscriber != null) {
            if (subscriber.isSubscriberReady()) {
                subscriber.objectModified(message);
            }
        }
    }

    public String getMessgeText(String message) {
        return message;
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

    public synchronized void setButtons(SendMessage sendMessage) {
        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardARow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardARow.add(new KeyboardButton("1"));

        // Вторая строчка клавиатуры
        KeyboardRow keyboardBRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardBRow.add(new KeyboardButton("2"));
        KeyboardRow keyboardCRow = new KeyboardRow();
        keyboardCRow.add(new KeyboardButton("3"));
        KeyboardRow keyboardDRow = new KeyboardRow();
        keyboardDRow.add(new KeyboardButton("4"));

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardARow);
        keyboard.add(keyboardBRow);
        keyboard.add(keyboardCRow);
        keyboard.add(keyboardDRow);
        // и устанваливаем этот список нашей клавиатуре
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
