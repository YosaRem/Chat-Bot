package game;

import publisher_subscriber.ISubscriber;
import taks_models.QuizTask;
import writers.IWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class QuizLogic implements ISubscriber<String> {
    private Player player;
    private QuizGame game;
    private IWriter writer;
    private QuizTask currentTask;

    public QuizLogic(IWriter writer, Player player, QuizGame game) {
        this.writer = writer;
        this.player = player;
        this.game = game;
    }

    public void startGame() {
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
            currentTask = game.getTask();
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

    public QuizTask getCurrentTask() {
        return currentTask;
    }

    public QuizTask deleteIncorrectAnswer() {
        currentTask = game.deleteTwoIncorrectAnswers();
        return currentTask;
    }

    public boolean useOnlyTwoAnswer() {
        return player.useOnlyTwoAnswer();
    }

    public int getScore() {
        return player.getScore();
    }

    public int getLevel() {
        return game.getLevel();
    }

    @Override
    public void objectModified(String data) {
        continueGame(data);
    }
}