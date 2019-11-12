package game;

import publisher_subscriber.ISubscriber;
import readers.LineReader;
import taks_models.QuizTask;
import taks_models.QuizTaskValue;
import writers.IWriter;

import java.io.*;
import java.util.ArrayList;

public class QuizLogic implements ISubscriber<String> {
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
                writer.print("Игрок - " + player.getName() + "\nВаш уровень - "
                        + game.getLevel() + "\nВаши очки - " + player.getScore());
                break;
            }
            case "/del": {
                if (player.useOnlyTwoAnswer()) {
                    QuizTask task = game.deleteTwoIncorrectAnswers();
                    printTask(task);
                } else {
                    writer.print("Вы уже использовали эту возможность");
                }
                break;
            }
            default: {
                try {
                    checkInputAnswer(Integer.parseInt(input));
                } catch (NumberFormatException e) {
                    writer.print("Ответ введён неверно. Попробуйте ещё раз.");
                    return;
                }
                declaimTask();
            }
        }
    }

    private void checkInputAnswer(Integer value) {
        if (game.checkAnswer(value)) {
            writer.print("Правильно!");
            player.increaseScore(game.getTaskPrice());
            game.incrementLevel();
            return;
        }
        writer.print("Увы, но это не так.\n" + "Правильный ответ - " + game.getRightAnswer());
        player.makeMistake();
        game.playerMadeMistake();
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
            ArrayList<String> lines = new LineReader(file).read();
            writer.print(String.join("\n", lines));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printTask(QuizTask task) {
        writer.print(new QuizTaskValue(task).getDescription());
    }

    @Override
    public void objectModified(String data) {
        if (isSubscriberReady)
            continueGame(data);
    }
}