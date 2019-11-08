package game;

import taks_models.QuizTask;
import tasks_extractor.QuizTasksExtractor;

import java.io.IOException;

public class QuizGame {
    private int level;
    private QuizTasksExtractor extractor;
    private QuizTask currentTask;
    private final int[] variants = new int[]{500, 1000, 2000, 3000, 5000, 10000, 15000,
            25000, 50000, 100000, 200000, 400000, 800000, 1500000, 3000000};

    public QuizGame(QuizTasksExtractor extractor) {
        this.extractor = extractor;
        level = 0;
    }

    public void incrementLevel() {
        if (level < variants.length - 1) {
            level++;
        } else {
            level = 0;
        }
    }

    public int getLevel() {
        return level;
    }

    public int getTaskPrice() {
        return variants[level];
    }

    public void playerMadeMistake() {
        level = 0;
    }

    public boolean checkAnswer(Integer answer) {
        return currentTask.checkAnswer(answer);
    }

    public QuizTask getTask() throws IOException {
        currentTask = extractor.getRandomTaskConsiderLevel(level);
        return currentTask;
    }

    public String getRightAnswer() {
        return currentTask.getRightAnswer();
    }

    public QuizTask deleteTwoIncorrectAnswers() {
        currentTask = currentTask.deleteTwoIncorrectAnswer();
        return currentTask;
    }
}
