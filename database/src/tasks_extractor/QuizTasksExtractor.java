package tasks_extractor;

import readers.LineReader;
import taks_models.QuizTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static tasks_extractor.QuizStringName.CORRECT;
import static tasks_extractor.QuizStringName.QUESTION;

public class QuizTasksExtractor implements Extractor {
    private int amountFiles;
    private String path;

    public QuizTasksExtractor(String path) {
        this.path = path;
        amountFiles = Objects.requireNonNull(new File(this.path).list()).length;
    }

    public QuizTask getRandomTask() throws IOException {
        Random rnd = new Random();
        ArrayList<String> strings = getTaskStringsFromFile(rnd.nextInt(amountFiles));
        return taskConstructor(strings);
    }

    private ArrayList<String> getTaskStringsFromFile(int fileNumber) throws IOException {
        File file = new File(path + "//" + fileNumber + ".txt");
        LineReader lineReader = new LineReader(file, 5);
        lineReader.read();
        return lineReader.getData();
    }

    private QuizTask taskConstructor(ArrayList<String> strings) {
        return new QuizTask(strings.get(QUESTION.getIndex()), strings.get(CORRECT.getIndex()),
                strings.subList(CORRECT.getIndex() + 1, strings.size()));
    }
}