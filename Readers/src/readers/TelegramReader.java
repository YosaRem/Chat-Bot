package readers;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import chatBote.TelegramBot;
import publisher_subscriber.IPublisher;
import publisher_subscriber.ISubscriber;

import java.io.IOException;

public class TelegramReader implements IReader, IPublisher {
    private ISubscriber subscriber;
    private TelegramBot bot;

    public TelegramReader(ISubscriber subscriber, TelegramBot bot) {
        this.subscriber = subscriber;
        this.bot = bot;
    }

    @Override
    public String read() throws IOException {
        return null;
    }

    @Override
    public void subscribe(ISubscriber subscriber) {
        this.subscriber = subscriber;
    }
}
