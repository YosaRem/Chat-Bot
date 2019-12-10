package commands;


import chatBot.TelegramMesData;
import writers.ITelegramWriterFactory;
import writers.IWriter;

public interface ICommand {
    void justDoIt(TelegramMesData data, ITelegramWriterFactory writerFactory);
}
