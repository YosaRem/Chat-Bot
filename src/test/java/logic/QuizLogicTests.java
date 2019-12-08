package logic;

import static org.junit.jupiter.api.Assertions.*;

import game.Player;
import game.QuizGame;
import game.QuizLogic;
import org.junit.jupiter.api.Test;
import tasks_extractor.QuizTasksExtractor;

import java.io.IOException;

public class QuizLogicTests {
    @Test
    public void checkingUnacceptableAnswer() throws IOException {
        FakeWriter writer = new FakeWriter();
        FakeReader reader = new FakeReader();
        QuizGame game = new QuizGame(new QuizTasksExtractor("src/main/resources/questions"));
        Player player = new Player("Test");
        QuizLogic logic = new QuizLogic(writer, player, game);

        reader.subscribe(logic);
        logic.startGame();
        reader.setInput("some wrong answer");
        reader.read();

        assertEquals("Ответ введён неверно. Попробуйте ещё раз.", writer.getOutput());
    }

    @Test
    public void checkingWrongAnswer() throws IOException {
        FakeWriter writer = new FakeWriter();
        FakeReader reader = new FakeReader();
        QuizGame game = new QuizGame(new QuizTasksExtractor("src/main/resources/questions"));
        Player player = new Player("Test");
        QuizLogic logic = new QuizLogic(writer, player, game);

        reader.subscribe(logic);
        logic.startGame();
        String expectedAnswer = writer.getOutput();
        reader.setInput("0");
        reader.read();

        assertNotEquals(expectedAnswer, writer.getOutput());
    }
}
