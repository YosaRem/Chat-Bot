package commands;

import game.QuizLogic;
import org.glassfish.grizzly.compression.lzma.impl.Base;

import java.util.ArrayList;

public class HelpCommand extends BaseCommand {
    private static final HelpCommand helpcommand = new HelpCommand();

    public HelpCommand() {
        super("/help");
    }

    @Override
    public String getDescription() {
        return getName() + " Выводит информацию о командахю";
    }

    public static HelpCommand getInstance() {
        return helpcommand;
    }

    @Override
    public void justDoIt(QuizLogic logic) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Это чат-бот, который позволяет играть в викторину.");
        for (BaseCommand command : CommandConverter.getAllCommands()) {
            lines.add(command.getDescription());
        }
        logic.getWriter().printMsg(String.join("\n", lines));
    }
}
