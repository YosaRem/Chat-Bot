package tasks_extractor;

import taks_models.QuizTask;
import taks_models.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static tasks_extractor.QuizStringName.CORRECT;
import static tasks_extractor.QuizStringName.QUESTION;

public class QuizTasksExtractor implements Extractor {
    private int amountFiles;
    private String pathTasks;

    public QuizTasksExtractor(String path) {
        pathTasks = path;
        amountFiles = Objects.requireNonNull(new File(pathTasks).listFiles()).length;
    }

    public QuizTask getRandomTask() throws IOException {
        Random rnd = new Random();
        List<String> strings = getTaskStringsFromFile(rnd.nextInt(amountFiles));
        return taskConstructor(strings);
    }

    private List<String> getTaskStringsFromFile(Integer fileNumber) throws IOException {
        List<String> strings = new ArrayList<>();
        File file = new File(pathTasks + "//" + fileNumber + ".txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader lineReader = new BufferedReader(fileReader);
        String line = lineReader.readLine();
        while (line != null && strings.size() < 5) {
            strings.add(line);
            line = lineReader.readLine();
        }
        return strings;
    }

    private QuizTask taskConstructor(List<String> strings) {
        return new QuizTask(strings.get(QUESTION.getIndex()), strings.get(CORRECT.getIndex()),
                strings.subList(CORRECT.getIndex() + 1, strings.size()));
    }
}