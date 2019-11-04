package tests.logic;

import publisher_subscriber.IPublisher;
import publisher_subscriber.ISubscriber;
import readers.IReader;

import java.io.IOException;

public class FakeReader implements IReader, IPublisher {
    private ISubscriber subscriber;
    private String input = "input";

    @Override
    public String read() throws IOException {
        return input;
    }

    @Override
    public void subscribe(ISubscriber subscriber) {
        this.subscriber = subscriber;
    }

    public String getInput() {
        return input;
    }
}
