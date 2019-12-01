package logic;

import taks_models.QuizTask;
import writers.IWriter;

public class FakeWriter implements IWriter {
    public String output;

    public String getOutput() {
        return output;
    }

    @Override
    public void printMsg(String message) {
        output = message;
    }

    @Override
    public void printTask(QuizTask task) {
        return;
    }
}
