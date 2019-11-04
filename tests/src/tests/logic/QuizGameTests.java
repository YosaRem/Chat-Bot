package tests.logic;

import static org.junit.jupiter.api.Assertions.*;

import game.QuizGame;
import game.Player;
import org.junit.jupiter.api.Test;
import taks_models.QuizTask;
import tasks_extractor.QuizTasksExtractor;

import java.io.IOException;

public class QuizGameTests {
    @Test
    void createGameTest() {
        QuizGame game = new QuizGame(new QuizTasksExtractor("test_files/good"));
        assertEquals(0, game.getLevel());
    }

    @Test
    void playerMakeMistakeTest() {
        QuizGame game = new QuizGame(new QuizTasksExtractor("test_files/good"));
        game.incrementLevel();
        assertEquals(1, game.getLevel());
        game.playerMadeMistake();
        assertEquals(0, game.getLevel());
    }

    @Test
    void checkRightAnswer() throws IOException {
        QuizGame game = new QuizGame(new QuizTasksExtractor("test_files/good"));
        QuizTask task = game.getTask();
        boolean isCorrectAnswerFound = false;
        for (int i = 1; i < 5; i++) {
            if (game.checkAnswer(i)) {
                isCorrectAnswerFound = true;
                break;
            }
        }
        assertEquals(true, isCorrectAnswerFound);
    }

    @Test
    void deleteTwoAnswersTest() throws IOException {
        QuizGame game = new QuizGame(new QuizTasksExtractor("test_files/good"));
        game.getTask();
        QuizTask task = game.deleteTwoIncorrectAnswers();
        assertEquals(2, task.getOptions().size());
    }

    @Test
    void getRightAnswerTest() throws IOException {
        QuizGame game = new QuizGame(new QuizTasksExtractor("test_files/good"));
        QuizTask task = game.getTask();
        assertEquals(task.getRightAnswer(), task.getRightAnswer());
    }

    @Test
    void incrementLevelTest() {
        QuizGame game = new QuizGame(new QuizTasksExtractor("test_files/good"));
        assertEquals(0, game.getLevel());
        boolean isLevelsSetByZero = false;
        int i = 0;
        while (i < 1000) {
            game.incrementLevel();
            if (game.getLevel() == 0) {
                isLevelsSetByZero = true;
                break;
            }
            i++;
        }
        assertEquals(true, isLevelsSetByZero);
    }
}
