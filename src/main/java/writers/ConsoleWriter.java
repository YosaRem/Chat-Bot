package writers;

import taks_models.QuizTask;
import taks_models.QuizTaskValue;

public class ConsoleWriter implements IWriter {

    @Override
    public void printMsg(String message) {
        System.out.println(message);
    }

    @Override
    public void printTask(QuizTask task) {
        System.out.println(new QuizTaskValue(task).getDescription());
    }
}
