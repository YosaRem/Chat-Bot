package commands;

import chatBot.TelegramMesData;
import chatBot.keyboards.HintKeyboard;
import game.QuizLogic;
import writers.ITelegramWriterFactory;
import writers.IWriter;
import writers.WriterBuilder;

public class HintCommand extends BaseCommand {
    private QuizLogic logic;

    public HintCommand(QuizLogic logic) {
        super("/hints","Подсказка");
        this.logic = logic;
    }

    @Override
    public String getDescription() {
        return getName() + " Показывает доступные подсказки.";
    }

    @Override
    public void justDoIt(TelegramMesData data, ITelegramWriterFactory writerFactory) {
        IWriter writer = writerFactory
                .compile(data.getChatId(),new HintKeyboard(logic
                        .getCurrentTask()
                        .getQuestion()));
        writer.printMsg("Выберите подсказку, которую хотите использовать");
    }
}
