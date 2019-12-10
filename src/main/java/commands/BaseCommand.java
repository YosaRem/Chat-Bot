package commands;


public abstract class BaseCommand implements ICommand {
    private String name;
    private String russianName;

    public BaseCommand(String name, String russianName) {
        this.name = name;
        this.russianName = russianName;
    }

    public abstract String getDescription();

    public String getName() {
        return name;
    }

    public String getRussianName() { return russianName; }
}
