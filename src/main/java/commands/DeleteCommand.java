package commands;

import taks_models.QuizTask;

public class DeleteCommand extends BaseCommand {
    private static final DeleteCommand deleteCommand = new DeleteCommand();

    public DeleteCommand() {
        super("/del");
    }

    @Override
    public void justDoIt(CommandData data) {
        if (data.quizLogic.getPlayer().useOnlyTwoAnswer()) {
            QuizTask task = data.quizLogic.getGame().deleteTwoIncorrectAnswers();
            data.quizLogic.getWriter().printTask(task);
        } else {
            data.quizLogic.getWriter().printMsg("Вы уже использовали эту возможность");
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
