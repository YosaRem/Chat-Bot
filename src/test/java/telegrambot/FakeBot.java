package telegrambot;

import chatBot.TelegramBot;
import org.telegram.telegrambots.api.objects.Update;

public class FakeBot extends TelegramBot {
    public String ready;

    @Override
    public void onUpdateReceived(Update update) {
    }

    public void sendMsg(String chatId, String s) {
        ready=s;
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
