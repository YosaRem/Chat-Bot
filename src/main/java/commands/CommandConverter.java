package commands;


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

    public static void defineCommands(String system) {
        addCommand(StartCommand.getInstance());
        addCommand(DeleteCommand.getInstance());
        addCommand(ScoresCommand.getInstance());
        addCommand(HelpCommand.getInstance());
        if (system.equals("Telegram")) {
            addCommand(HelpFriendCommand.getInstance());
            addCommand(HintCommand.getInstance());
            allCommands.put("Помощь", HelpCommand.getInstance());
            allCommands.put("Подсказка", HintCommand.getInstance());
        }
    }

    public static boolean canConvert(String input) {
        return allCommands.containsKey(input);
    }
}
