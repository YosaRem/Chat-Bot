package writers;

public class ConsoleWriter implements IWriter {

    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
