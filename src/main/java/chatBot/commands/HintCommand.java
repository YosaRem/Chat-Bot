package chatBot.commands;

import chatBot.HintKeyboard;
import game.QuizLogic;
import writers.TelegramWriter;

public class HintCommand extends BaseCommand {
    public static final HintCommand hintCommand = new HintCommand();

    public HintCommand() {
        super("/hints");
    }

    @Override
    public String getDescription() {
        return getName() + " Показывает доступные подсказки.";
    }

    @Override
    public void justDoIt(CommandData data) {
        TelegramWriter writer = (TelegramWriter) data.logic.getWriter();
        writer.setKeyboard(new HintKeyboard(data.logic.getGame().getCurrentQuestion()));
        writer.printMsg("Выберите подсказку, которую хотите использзовать");
    }

    public static BaseCommand getInstance() {
        return hintCommand;
    }
}
