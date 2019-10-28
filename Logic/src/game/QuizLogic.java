package game;

import publisher_subscriber.ISubscriber;
import readers.LineReader;
import taks_models.QuizTask;
import taks_models.QuizTaskValue;
import writers.IWriter;

import java.io.*;

public class QuizLogic implements ISubscriber {
    private Player player;
    private QuizGame game;
    private IWriter writer;
    private String path;

    public QuizLogic(IWriter writer, Player player, QuizGame game, String instructionsPath) {
        this.writer = writer;
        this.player = player;
        this.game = game;
        this.path = instructionsPath;
    }

    public void startGame() {
        printHelp();
        try {
            QuizTask task = game.getTask();
            printTask(task);
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
                    currentTask = extractor.getRandomTask(new QuizGame());
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

    private void printTask(QuizTask task) {
        writer.print(new QuizTaskValue(task).getDescription());
    }

    @Override
    public void objectModified(String data) {
        continueGame(data);
    }

    @Override
    public boolean isSubscriberReady() {
        return false;
    }
}