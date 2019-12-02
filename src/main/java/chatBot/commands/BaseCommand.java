package chatBot.commands;


public abstract class BaseCommand implements ICommand {
    private String name;

    public BaseCommand(String name) {
        this.name = name;
        System.out.println("WAS " + name);
    }

    public abstract String getDescription();

    public String getName() {
        return name;
    }
}
