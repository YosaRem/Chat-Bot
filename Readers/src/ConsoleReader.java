import publisher_subscriber.ISubscriber;

import java.io.InputStream;
import java.util.Scanner;

public class ConsoleReader implements IReader {
    private ISubscriber subscriber;
    private InputStream in;

    public ConsoleReader(ISubscriber subscriber, InputStream in) {
        this.in=in;
        this.subscriber = subscriber;
    }

    @Override
    public void read() {
        Scanner scanner = new Scanner(in);
        String consoleInput = "";
        if (scanner.hasNextLine()) {
            consoleInput = scanner.nextLine();
        }
        subscriber.objectModified(consoleInput);
    }
}
