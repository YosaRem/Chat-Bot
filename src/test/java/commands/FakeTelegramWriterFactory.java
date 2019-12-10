package commands;

import chatBot.ITelegramBot;
import chatBot.keyboards.IKeyboard;
import logic.FakeWriter;
import writers.ITelegramWriterFactory;
import writers.IWriter;
import writers.WriterBuilder;

public class FakeTelegramWriterFactory implements ITelegramWriterFactory {
    private ITelegramBot tgBot;
    private FakeWriter writer;

    @Override
    public ITelegramWriterFactory setBot(ITelegramBot tgBot) {
        this.tgBot = tgBot;
        writer = null;
        return this;
    }

    @Override
    public ITelegramWriterFactory setMsgKeyboard(IKeyboard keyboard) {
        return this;
    }

    @Override
    public IWriter compile(String chatId) {
        FakeWriter writer = new FakeWriter();
        this.writer = writer;
        return writer;
    }

    public FakeWriter getWriter(){
        return writer;
    }
}
