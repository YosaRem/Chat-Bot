package chatBot;

import chatBot.keyboards.IKeyboard;
import publisher_subscriber.IPublisher;

public interface ITelegramBot extends IPublisher {
    void sendMsg(String chatId, String s, IKeyboard keyboard);
}
