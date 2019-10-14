import publisher_subscriber.ISubscriber;

import java.util.Scanner;

public class ConsoleReader implements IReader {
    private String input = "";
    private ISubscriber subscriber;

    public ConsoleReader(ISubscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public String getInput() {
        return input;
    }

    @Override
    public void read() {
        Scanner scanner = new Scanner(System.in);
        String consoleInput = "";
        if (scanner.hasNextLine()) {
            consoleInput = scanner.nextLine();
        }
        input = consoleInput;
        subscriber.objectModified();
    }

    @Override
    public void continueRead() {
        read();
    }
}
