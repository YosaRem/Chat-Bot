package writers;

import chatBot.TelegramBot;

public class TelegramWriter implements IWriter {
    private TelegramBot bot;
    private String userId;

    public TelegramWriter(TelegramBot telegramBot, String userId) {
        bot = telegramBot;
        this.userId = userId;
    }

    @Override
    public void print(String message) {
        bot.sendMsg(userId, message);
    }
}
