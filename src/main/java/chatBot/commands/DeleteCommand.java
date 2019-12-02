package chatBot.commands;

import game.QuizLogic;
import taks_models.QuizTask;

public class DeleteCommand extends BaseCommand {
    private static final DeleteCommand deleteCommand = new DeleteCommand();

    public DeleteCommand() {
        super("/del");
    }

    @Override
    public void justDoIt(CommandData data) {
        if (data.logic.getPlayer().useOnlyTwoAnswer()) {
            QuizTask task = data.logic.getGame().deleteTwoIncorrectAnswers();
            data.logic.getWriter().printTask(task);
        } else {
            data.logic.getWriter().printMsg("Вы уже использовали эту возможность");
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
