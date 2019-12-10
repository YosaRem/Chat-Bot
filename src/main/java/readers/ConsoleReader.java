package readers;

import publisher_subscriber.IPublisher;
import publisher_subscriber.ISubscriber;

import java.io.InputStream;
import java.util.Scanner;

public class ConsoleReader implements IReader, IPublisher {
    private ISubscriber subscriber;
    private InputStream in;

    public ConsoleReader(InputStream in) {
        this.in = in;
    }

    @Override
    public String read() {
        Scanner scanner = new Scanner(in);
        String consoleInput = "";
        if (scanner.hasNextLine()) {
            consoleInput = scanner.nextLine();
        }
        if (subscriber != null) {
            subscriber.objectModified(consoleInput);
        }
        return consoleInput;
    }

    @Override
    public void subscribe(ISubscriber subscriber) {
        this.subscriber = subscriber;
    }
}
