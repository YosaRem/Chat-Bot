package commands;

import game.Player;
import game.QuizGame;
import game.QuizLogic;
import taks_models.QuizTask;
import writers.IWriter;

public class DeleteCommand extends BaseCommand {
    private static final DeleteCommand deleteCommand = new DeleteCommand();

    public DeleteCommand() {
        super("/del");
    }

    @Override
    public void justDoIt(QuizLogic logic) {
        if (logic.getPlayer().useOnlyTwoAnswer()) {
            QuizTask task = logic.getGame().deleteTwoIncorrectAnswers();
            logic.getWriter().printTask(task);
        } else {
            logic.getWriter().printMsg("Вы уже использовали эту возможность");
        }
    }

    public static BaseCommand getInstance() {
        return deleteCommand;
    }

    @Override
    public String getDescription() {
        return getName() + " Удаляет два неверных ответа. Доступно один раз за игру.";
    }
}
