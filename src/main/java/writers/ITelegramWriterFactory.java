package writers;

import chatBot.ITelegramBot;
import chatBot.keyboards.IKeyboard;

public interface ITelegramWriterFactory {
    IWriter compile(String chatId, IKeyboard keyboard);
}
