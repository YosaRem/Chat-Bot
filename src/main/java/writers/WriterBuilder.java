package writers;

import chatBot.ITelegramBot;
import chatBot.keyboards.IKeyboard;
import chatBot.keyboards.StandardKeyboard;

public class WriterBuilder implements ITelegramWriterFactory {
    private static ITelegramBot bot;
    private IKeyboard msgKeyboard;

    public WriterBuilder() {
        this.msgKeyboard = new StandardKeyboard();
    }

    @Override
    public ITelegramWriterFactory setBot(ITelegramBot tgBot) {
        bot = tgBot;
        return this;
    }

    @Override
    public ITelegramWriterFactory setMsgKeyboard(IKeyboard keyboard) {
        this.msgKeyboard = keyboard;
        return this;
    }

    @Override
    public IWriter compile(String chatId) {
        return new TelegramWriter(bot, chatId, msgKeyboard);
    }
}
