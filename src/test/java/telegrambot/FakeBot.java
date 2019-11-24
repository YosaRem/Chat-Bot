package telegrambot;

import chatBot.IKeyboard;
import chatBot.ITelegramBot;
import chatBot.TelegramBot;
import org.telegram.telegrambots.api.objects.Update;
import publisher_subscriber.IPublisher;
import publisher_subscriber.ISubscriber;

public class FakeBot implements ITelegramBot {
    public String text;
    public String lastChatId;

    @Override
    public void sendMsg(String chatId, String s, IKeyboard keyboard) {
        lastChatId = chatId;
        text = s;
    }


    @Override
    public void subscribe(ISubscriber subscriber) {
    }
}
