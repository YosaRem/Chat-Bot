import publisher_subscriber.ISubscriber;
import taks_models.QuizTaskValue;
import taks_models.Task;
import tasks_extractor.QuizTasksExtractor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Game implements ISubscriber {
    private QuizTasksExtractor extractor;
    private IReader reader;
    private IWriter writer;
    private Task currentTask;

    public Game(QuizTasksExtractor extractor, IWriter writer) {
        this.extractor = extractor;
        this.reader = new ConsoleReader(this);
        this.writer = writer;
    }

    public void startGame() {
        printHelp();
        while (true) {
            currentTask = extractor.getRandomTask();
            printTask();
            reader.continueRead();
        }
    }

    private void continueGame() {
        switch (reader.getInput()) {
            case "/help": {
                printHelp();
                break;
            }
            default: {
                try {
                    String answer = reader.getInput();
                    if (currentTask.checkAnswer(answer)) {
                        writer.print("Правильно!");
                    } else {
                        writer.print("Увы, но это не так.");
                        writer.print("Правильный ответ - " + currentTask.getRightAnswer());
                    }
                } catch (NullPointerException | NumberFormatException e) {
                    writer.print("Неправильный ответ!");
                    writer.print("Правильный ответ - " + currentTask.getRightAnswer());
                }
            }
        }
    }

    private void printHelp() {
        List<String> strings = new ArrayList<>();
        File file = new File("resources/help.txt");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader lineReader = new BufferedReader(fileReader);
            String line = lineReader.readLine();
            while (line != null) {
                writer.print(line);
                line = lineReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printTask() {
        writer.print(currentTask.getTaskToString());
    }

    @Override
    public void objectModified() {
        continueGame();
    }
}