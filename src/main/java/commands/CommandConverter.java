package commands;


import chatBot.UserData;
import game.QuizLogic;

import java.util.Collection;
import java.util.HashMap;

public class CommandConverter {
    private final HashMap<String, BaseCommand> allCommands = new HashMap<>();
    private final QuizLogic logic;
    private final HashMap<UserData, QuizLogic> subscribers;

    public CommandConverter(QuizLogic logic, HashMap<UserData, QuizLogic> subscribers) {
        this.logic = logic;
        this.subscribers = subscribers;
        defineCommands();
    }

    public void addCommand(BaseCommand command) {
        allCommands.put(command.getName(), command);
        allCommands.put(command.getRussianName(), command);
    }

    public BaseCommand getCommand(String name) {
        return allCommands.get(name.split("_")[0]);
    }

    public Collection<BaseCommand> getAllCommands() {
        return allCommands.values();
    }

    private void defineCommands() {
        addCommand(new StartCommand(this.logic));
        addCommand(new DeleteCommand(this.logic));
        addCommand(new ScoresCommand(this.logic));
        addCommand(new HintCommand(this.logic));
        addCommand(new HelpCommand(this));
        addCommand(new HelpFriendCommand(this.subscribers));
        addCommand(new ResendRequestCommand(this.subscribers));
        addCommand(new SendHelpAnswerCommand(this.subscribers));
    }

    public boolean canConvert(String input) {
        String tmp = input.split("_")[0];
        return allCommands.containsKey(tmp);
    }
}
