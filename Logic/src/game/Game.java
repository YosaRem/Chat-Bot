package game;

import publisher_subscriber.ISubscriber;
import readers.LineReader;
import taks_models.QuizTask;
import taks_models.QuizTaskValue;
import tasks_extractor.QuizTasksExtractor;
import writers.IWriter;

import java.io.*;

public class Game implements ISubscriber {
    private QuizTasksExtractor extractor;
    private IWriter writer;
    private QuizTask currentTask;
    private String path;

    public Game(QuizTasksExtractor extractor, IWriter writer, String instructionsPath) {
        this.extractor = extractor;
        this.writer = writer;
        this.path = instructionsPath;
    }

    public void startGame() {
        printHelp();
        try {
            currentTask = extractor.getRandomTask(new Level());
            printTask();
        } catch (FileNotFoundException e) {
            writer.print("Не получается считать задание из файла " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void continueGame(String input) {
        switch (input) {
            case "/help": {
                printHelp();
                break;
            }
            default: {
                try {
                    if (currentTask.checkAnswer(input)) {
                        writer.print("Правильно!");
                    } else {
                        writer.print("Увы, но это не так.");
                        writer.print("Правильный ответ - " + currentTask.getRightAnswer());
                    }
                } catch (NumberFormatException e) {
                    writer.print("Неправильный ответ!");
                    writer.print("Правильный ответ - " + currentTask.getRightAnswer());
                }
                try {
                    currentTask = extractor.getRandomTask(new Level());
                    printTask();
                } catch (FileNotFoundException e) {
                    writer.print("Не получается считать задание из файла " + e.getMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void printHelp() {
        File file = new File(path);
        try {
            LineReader lineReader = new LineReader(file);
            lineReader.read();
            writer.print(lineReader.getDataToLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printTask() {
        writer.print(new QuizTaskValue(currentTask).getDescription());
    }

    @Override
    public void objectModified(String data) {
        continueGame(data);
    }
}