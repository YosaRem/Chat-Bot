package writers;

import chatBot.ITelegramBot;
import chatBot.TelegramBot;

public class TelegramWriter implements IWriter {
    private ITelegramBot bot;
    private String userId;

    public TelegramWriter(ITelegramBot telegramBot, String userId) {
        bot = telegramBot;
        this.userId = userId;
    }

    @Override
    public void print(String message) {
        bot.sendMsg(userId, message);
    }
}
