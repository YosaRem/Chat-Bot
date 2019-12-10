package writers;

import chatBot.ITelegramBot;
import chatBot.keyboards.IKeyboard;
import chatBot.keyboards.StandardKeyboard;

public class WriterBuilder implements ITelegramWriterFactory {
    private ITelegramBot bot;

    public WriterBuilder(ITelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public IWriter compile(String chatId, IKeyboard keyboard) {
        return new TelegramWriter(bot, chatId, keyboard);
    }
}
