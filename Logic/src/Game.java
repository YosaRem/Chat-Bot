import publisher_subscriber.ISubscriber;
import taks_models.Task;
import tasks_extractor.QuizTasksExtractor;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Game implements ISubscriber {
    private QuizTasksExtractor extractor;
    private IReader reader;
    private IWriter writer;
    private Task currentTask;
    private boolean endGame = false;
    private Integer rightAnswersCounter = 0;

    public Game(QuizTasksExtractor extractor, IWriter writer) {
        this.extractor = extractor;
        this.reader = new ConsoleReader(this);
        this.writer = writer;
    }

    public void startGame() {
        printHelp();
        while (!endGame) {
            currentTask = extractor.getRandomTask();
            printTask();
            reader.continueRead();
        }
        writer.print("Количество верных ответов: " + rightAnswersCounter.toString());
        writer.print("Спасибо, что играли!!!");
    }

    private void continueGame() {
        switch (reader.getInput())
        {
            case "help": {
                printHelp();
                break;
            }
            case "exit": {
                endGame = true;
                break;
            }
            default: {
                try {
                    String answer = reader.getInput();
                    if (currentTask.checkAnswer(answer)) {
                        writer.print("Правильно!");
                        rightAnswersCounter += 1;
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
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            BufferedReader lineReader = new BufferedReader(fileReader);
            String line = lineReader.readLine();
            while (line != null) {
                writer.print(line);;
                line = lineReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printTask() {
        String[] task = currentTask.getTaskToString();
        for (String s : task) {
            writer.print(s);
        }
    }

    @Override
    public void objectModified(Object obj) {
        continueGame();
    }
}
