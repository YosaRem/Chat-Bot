package commands;

import chatBot.TelegramMesData;
import game.QuizLogic;
import taks_models.QuizTask;
import writers.IWriter;
import writers.WriterBuilder;

public class DeleteCommand extends BaseCommand {
    private QuizLogic logic;

    public DeleteCommand(QuizLogic logic) {
        super("/del");
        this.logic = logic;
    }

    @Override
    public void justDoIt(TelegramMesData data) {
        IWriter writer = new WriterBuilder(data.getChatId()).compile();
        if (this.logic.useOnlyTwoAnswer()) {
            QuizTask task = this.logic.deleteIncorrectAnswer();
            writer.printTask(task);
        } else {
            writer.printMsg("Вы уже использовали эту возможность");
        }
    }

    @Override
    public String getDescription() {
        return getName() + " Удаляет два неверных ответа. Доступно один раз за игру.";
    }
}
