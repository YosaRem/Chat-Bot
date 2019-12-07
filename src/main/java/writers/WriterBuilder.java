package writers;

import chatBot.ITelegramBot;
import chatBot.keyboards.IKeyboard;
import chatBot.keyboards.StandardKeyboard;

public class WriterBuilder {
    private static ITelegramBot bot;
    private String chatId;
    private IKeyboard msgKeyboard;

    public WriterBuilder(String chatId) {
        this.chatId = chatId;
        this.msgKeyboard = new StandardKeyboard();
    }

    public static void setBot(ITelegramBot tgBot) {
        bot = tgBot;
    }

    public WriterBuilder setMsgKeyboard(IKeyboard keyboard) {
        this.msgKeyboard = keyboard;
        return this;
    }

    public TelegramWriter compile() {
        return new TelegramWriter(bot, chatId, msgKeyboard);
    }
}
