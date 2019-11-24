package chatBot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import publisher_subscriber.ISubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelegramBot extends TelegramLongPollingBot implements ITelegramBot {
    private final String botName;
    private final String token;
    private ISubscriber telegramBotLogic;
    private static Logger log = Logger.getLogger(TelegramBot.class.getName());

    public TelegramBot(String botName, String token) {
        this.botName = botName;
        this.token = token;
    }


    @Override
    public void onUpdateReceived(Update update) {
        Message message;
        String text;
        if (update.hasMessage()) {
            message = update.getMessage();
            text=message.getText();
        } else if (update.hasCallbackQuery()) {
            message = update.getCallbackQuery().getMessage();
            text=update.getCallbackQuery().getData();
        } else {
            return;
        }
        String chatId = message.getChatId().toString();
        String firstName = message.getChat().getFirstName();
        printToConsole(message.getDate(), chatId, firstName, text);
        log.log(Level.INFO, "TEXT: ", text);
        telegramBotLogic.objectModified(new TelegramMesData(firstName, chatId, text));
    }

    @Override
    public synchronized void sendMsg(String chatId, String s, IKeyboard keyboard) {
        SendMessage sendMes = new SendMessage();
        System.out.println(s);
        sendMes.enableMarkdown(true);
        sendMes.setChatId(chatId);
        sendMes.setText(s);
        sendMes.setReplyMarkup(keyboard.getKeyboard());
        try {
            execute(sendMes);
        } catch (TelegramApiException e) {
            log.log(Level.SEVERE, "Exception: ", e.toString());
        }
    }

    private void printToConsole(Integer date, String chatId, String firstName, String message) {
        System.out.println("////////////////////////////////////////////////");
        System.out.println(date + " id: " + chatId);
        System.out.println("Player: " + firstName + ": " + message);
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

    @Override
    public void subscribe(ISubscriber subscriber) {
        telegramBotLogic = subscriber;
    }
}
