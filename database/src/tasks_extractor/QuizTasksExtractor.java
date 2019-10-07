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

    public QuizTasksExtractor() {
        String path = "resources/questions";
        pathTasks = path;
        amountFiles = Objects.requireNonNull(new File(path).listFiles()).length;
    }

    public Task getRandomTask() {
        Random rnd = new Random();
        List<String> strings = getTaskStringsFromFile(rnd.nextInt(amountFiles));
        return taskConstructor(strings);
    }

    public List<Task> getListAllTasks() {
        List<Task> result = new ArrayList<>();
            for (int i = 0; i < amountFiles; i++) {
                List<String> strings = getTaskStringsFromFile(i);
                Task task = taskConstructor(strings);
                result.add(task);
            }
        return result;
    }

    private List<String> getTaskStringsFromFile(Integer fileNumber) {
        List<String> strings = new ArrayList<>();
        try {
            File file = new File(pathTasks + "//" + fileNumber + ".txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader lineReader = new BufferedReader(fileReader);
            String line = lineReader.readLine();
            while (line != null) {
                strings.add(line);
                line = lineReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Не получается считать задание из файла " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

    private Task taskConstructor(List<String> strings) {
        return new QuizTask(strings.get(QUESTION.getIndex()), strings.get(CORRECT.getIndex()),
                            strings.subList(CORRECT.getIndex() + 1, strings.size()));

    }
}
