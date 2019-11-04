package tests.logic;

import static org.junit.jupiter.api.Assertions.*;

import game.QuizGame;
import game.Player;
import org.junit.jupiter.api.Test;
import tasks_extractor.QuizTasksExtractor;

public class QuizGameTests {
    @Test
    void createGameTest() {
        QuizGame game = new QuizGame(new QuizTasksExtractor("test_files/good"));
    }
}
