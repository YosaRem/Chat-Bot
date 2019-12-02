package chatBot.commands;

import game.QuizLogic;

import java.util.ArrayList;

public class HelpCommand extends BaseCommand {
    private static final HelpCommand helpcommand = new HelpCommand();

    public HelpCommand() {
        super("/help");
    }

    @Override
    public String getDescription() {
        return getName() + " Выводит информацию о командах.";
    }

    public static HelpCommand getInstance() {
        return helpcommand;
    }

    @Override
    public void justDoIt(CommandData data) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Это чат-бот, который позволяет играть в викторину.");
        for (BaseCommand command : CommandConverter.getAllCommands()) {
            lines.add(command.getDescription());
        }
        data.logic.getWriter().printMsg(String.join("\n", lines));
    }
}
