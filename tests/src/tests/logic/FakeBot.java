package tests.logic;

import chatBote.TelegramBot;
import org.telegram.telegrambots.meta.api.objects.Update;

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
