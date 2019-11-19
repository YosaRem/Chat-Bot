package logic;

import publisher_subscriber.IPublisher;
import publisher_subscriber.ISubscriber;
import readers.IReader;

import java.io.IOException;

public class FakeReader implements IReader, IPublisher {
    private ISubscriber subscriber;
    private String input = "input";

    @Override
    public String read() throws IOException {
        subscriber.objectModified(input);
        return input;
    }

    @Override
    public void subscribe(ISubscriber subscriber) {
        this.subscriber = subscriber;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }
}
