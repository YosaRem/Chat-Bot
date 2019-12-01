package commands;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class CommandConverter {
    public static final HashMap<String, BaseCommand> allCommands = new HashMap<>();

    public static void addCommand(BaseCommand command) {
        allCommands.put(command.getName(), command);
    }

    public static BaseCommand getCommand(String name) {
        return allCommands.get(name);
    }

    public static Collection<BaseCommand> getAllCommands() {
        return allCommands.values();
    }

    public static void defineCommands() {
        addCommand(StartCommand.getInstance());
        addCommand(DeleteCommand.getInstance());
        addCommand(ScoresCommand.getInstance());
        addCommand(HelpCommand.getInstance());
        addCommand(HelpFriendCommand.getInstance());
    }

    public static boolean canConvert(String input) {
        return allCommands.containsKey(input);
    }
}
