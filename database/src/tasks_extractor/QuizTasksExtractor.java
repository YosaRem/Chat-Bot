package tasks_extractor;

import taks_models.QuizTask;
import taks_models.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static tasks_extractor.QuizStringName.CORRECT;
import static tasks_extractor.QuizStringName.QUESTION;



public class QuizTasksExtractor implements Extractor {
    private int amountFiles;
    private String pathTasks;

    public QuizTasksExtractor(String path) {
        pathTasks = path;
        amountFiles = Objects.requireNonNull(new File(path).listFiles()).length;

    }

    /**
     * Return all quiz tasks from folder
     * @return List of QuizTasks
     */
    public List<Task> getListAllTasks() {
        List<Task> result = new ArrayList<>();
        try {
            for (int i = 0; i < amountFiles; i++) {
                List<String> strings = new ArrayList<>();
                File file = new File(pathTasks + "//" + i + ".txt");
                FileReader fileReader = new FileReader(file);
                BufferedReader lineReader = new BufferedReader(fileReader);
                String line = lineReader.readLine();
                while (line != null) {
                    strings.add(line);
                    line = lineReader.readLine();
                }
                Task task = new QuizTask(strings.get(QUESTION.getIndex()), strings.get(CORRECT.getIndex()),
                        strings.subList(CORRECT.getIndex() + 1, strings.size()));
                result.add(task);

            }
        } catch (FileNotFoundException e) {
            System.out.println("Не получается считать задание из файла " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
