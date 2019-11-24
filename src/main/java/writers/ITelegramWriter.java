package writers;

import taks_models.QuizTask;

public interface ITelegramWriter extends IWriter {
    void printTask(QuizTask task);
}
