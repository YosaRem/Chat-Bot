package tests.telegrambot;

import chatBote.TelegramMesData;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class FakeUpdate extends Update {
    TelegramMesData data;

    public FakeUpdate(String text, String chatId, String name) {
        data = new TelegramMesData(name, chatId, text);
    }
}
