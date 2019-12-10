package writers;

import taks_models.QuizTask;

public interface IWriter {
    void printMsg(String message);
    void printTask(QuizTask task);
}
