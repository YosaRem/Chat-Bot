package telegrambot;

import chatBot.TelegramBot;
import org.telegram.telegrambots.api.objects.Update;

public class FakeBot extends TelegramBot {
    public String text;
    public String lastChatId;

    public FakeBot(String botName, String token) {
        super(botName, token);
    }

    @Override
    public void onUpdateReceived(Update update) {
    }

    public void sendMsg(String chatId, String s) {
        lastChatId = chatId;
        text = s;
    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return null;
    }
}
