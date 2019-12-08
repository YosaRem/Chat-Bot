package commands;

import chatBot.TelegramMesData;
import writers.IWriter;
import writers.WriterBuilder;

import java.util.ArrayList;

public class HelpCommand extends BaseCommand {
    private final CommandConverter cc;

    public HelpCommand(CommandConverter cc) {
        super("/help");
        this.cc = cc;
    }

    @Override
    public String getDescription() {
        return getName() + " Выводит информацию о командах.";
    }

    @Override
    public void justDoIt(TelegramMesData data) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Это чат-бот, который позволяет играть в викторину.");
        for (BaseCommand command : this.cc.getAllCommands()) {
            if (command.getDescription() != null) {
                lines.add(command.getDescription());
            }
        }
        IWriter writer = new WriterBuilder(data.getChatId()).compile();
        writer.printMsg(String.join("\n", lines));
    }
}
