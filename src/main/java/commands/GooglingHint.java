package commands;

import chatBot.HintKeyboard;
import game.QuizLogic;
import writers.TelegramWriter;

public class GooglingHint {


    public void assist(QuizLogic logic) {
        TelegramWriter writer = (TelegramWriter) logic.getWriter();
        writer.setKeyboard(new HintKeyboard(logic.getGame().getCurrentQuestion()));
        writer.printMsg("Загуглить");
    }
}
