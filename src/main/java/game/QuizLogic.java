package game;

import commands.BaseCommand;
import commands.CommandConverter;
import publisher_subscriber.ISubscriber;
import readers.LineReader;
import taks_models.QuizTask;
import writers.IWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    }

    public void startGame() {
        CommandConverter.getCommand("/help").justDoIt(this);
        declaimTask();
        game.resetLevel();
        player.resetScore();
    }


    private void continueGame(String input) {
        try {
            checkInputAnswer(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            writer.printMsg("Ответ введён неверно. Попробуйте ещё раз.");
            return;
        }
        declaimTask();
    }

    private void checkInputAnswer(Integer value) {
        if (game.checkAnswer(value)) {
            writer.printMsg("Правильно!");
            player.increaseScore(game.getTaskPrice());
            game.incrementLevel();
            return;
        }
        writer.printMsg("Увы, но это не так.\n" + "Правильный ответ - " + game.getRightAnswer());
        player.resetScore();
        game.resetLevel();
    }

    private void declaimTask() {
        try {
            QuizTask currentTask = game.getTask();
            printTask(currentTask);
        } catch (FileNotFoundException e) {
            writer.printMsg("Не получается считать задание из файла " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printTask(QuizTask task) {
        writer.printTask(task);
    }

    public QuizGame getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public IWriter getWriter() {
        return writer;
    }

    @Override
    public void objectModified(String data) {
        if (CommandConverter.canConvert(data)) {
            CommandConverter.getCommand(data).justDoIt(this);
        }
        continueGame(data);
    }
}