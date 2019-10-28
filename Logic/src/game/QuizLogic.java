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
    private boolean isSubscriberReady;

    public QuizLogic(IWriter writer, Player player, QuizGame game, String instructionsPath) {
        this.writer = writer;
        this.player = player;
        this.game = game;
        this.path = instructionsPath;
        this.isSubscriberReady = false;
    }

    public void startGame() {
        printHelp();
        declaimTask();
        isSubscriberReady = true;
    }


    private void continueGame(String input) {
        switch (input) {
            case "/help": {
                printHelp();
                break;
            }
            case "/scores": {
                writer.print("Ваш уровень - " + game.getLevel());
                writer.print("Ваши очки - " + player.getScore());
                break;
            }
            default: {
                try {
                    checkInputAnswer(input);
                } catch (NumberFormatException e) {
                    writer.print("Ответ введён неверно. Попробуйте ещё раз.");
                    return;
                }
                declaimTask();
            }
        }
    }

    private boolean checkInputAnswer(String value) {
        if (game.checkAnswer(value)) {
            writer.print("Правильно!");
            player.increaseScore(game.getTaskPrice());
            game.incrementLevel();
            return true;
        }
        writer.print("Увы, но это не так.");
        writer.print("Правильный ответ - " + game.getRightAnswer());
        player.resetToZero();
        game.playerMadeMistake();
        return false;
    }

    private void declaimTask() {
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
        return isSubscriberReady;
    }
}