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
    private String path;

    public QuizTasksExtractor(String path) {
        this.path = path;
    }

    public QuizTask getRandomTaskConsiderLevel(int level) throws IOException {
        Random rnd = new Random();
        String dirPath = this.path + "//" + level;
        int amountFiles = Objects.requireNonNull(new File(dirPath).list()).length;
        ArrayList<String> strings = getTaskStringsFromFile(rnd.nextInt(amountFiles), dirPath);
        return taskConstructor(strings);
    }

    private ArrayList<String> getTaskStringsFromFile(int fileNumber, String dirPath) throws IOException {
        File file = new File(dirPath + "//" + fileNumber + ".txt");
        LineReader lineReader = new LineReader(file, 5);
        return lineReader.read();
    }

    private QuizTask taskConstructor(ArrayList<String> strings) {
        return new QuizTask(strings.get(QUESTION.getIndex()), strings.get(CORRECT.getIndex()),
                strings.subList(CORRECT.getIndex() + 1, strings.size()));
    }
}