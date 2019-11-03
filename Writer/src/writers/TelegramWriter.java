package writers;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import chatBote.TelegramBot;

public class TelegramWriter implements IWriter {
    private TelegramBot bot;
    private String userId;

    public TelegramWriter(TelegramBot bot, String userId) {
        this.bot = bot;
        this.userId = userId;
    }

    @Override
    public void print(String message) {
        bot.sendMsg(userId, message);
    }
}
