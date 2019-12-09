package writers;

import chatBot.ITelegramBot;
import chatBot.keyboards.IKeyboard;

public interface ITelegramWriterFactory {
    ITelegramWriterFactory setBot(ITelegramBot tgBot);
    ITelegramWriterFactory setMsgKeyboard(IKeyboard keyboard);
    IWriter compile(String chatId);
}
