package commands;

import chatBot.HintKeyboard;
import game.QuizLogic;
import writers.TelegramWriter;

public class GooglingHint extends BaseCommand {


    public GooglingHint() {
        super("/hints");
    }

    public void assist(QuizLogic logic) {
        TelegramWriter writer = (TelegramWriter) logic.getWriter();
        writer.setKeyboard(new HintKeyboard(logic.getGame().getCurrentQuestion()));
        writer.printMsg("Загуглить");
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void justDoIt(QuizLogic logic) {

    }
}
