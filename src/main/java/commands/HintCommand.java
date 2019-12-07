package commands;

import chatBot.TelegramMesData;
import chatBot.keyboards.HintKeyboard;
import game.QuizLogic;
import writers.IWriter;
import writers.TelegramWriter;
import writers.WriterBuilder;

public class HintCommand extends BaseCommand {
    private QuizLogic logic;

    public HintCommand(QuizLogic logic) {
        super("/hints");
        this.logic = logic;
    }

    @Override
    public String getDescription() {
        return getName() + " Показывает доступные подсказки.";
    }

    @Override
    public void justDoIt(TelegramMesData data) {
        IWriter writer = new WriterBuilder(data.getChatId())
                .setMsgKeyboard(new HintKeyboard(logic
                        .getCurrentTask()
                        .getQuestion()))
                .compile();
        writer.printMsg("Выберите подсказку, которую хотите использовать");
    }
}
