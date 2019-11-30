package logic;

import static org.junit.jupiter.api.Assertions.*;

import game.Player;
import org.junit.jupiter.api.Test;

public class PlayerTests {
    @Test
    void useOnlyTwoAnswersTest() {
        Player player = new Player("Name");
        assertTrue(player.useOnlyTwoAnswer());
        assertFalse(player.useOnlyTwoAnswer());
    }

    @Test
    void getPlayerNameAndScoresTest() {
        Player player = new Player("Name");
        assertEquals(0, player.getScore());
        assertEquals("Name", player.getName());
    }

    @Test
    void increaseScoresTest() {
        Player player = new Player("Name");
        int increaser = 100;
        for (int i = 0; i < 10; i++) {
            assertEquals(i * increaser, player.getScore());
            player.increaseScore(increaser);
        }
    }

    @Test
    void mistakeTest() {
        Player player = new Player("Name");
        player.increaseScore(100);
        assertEquals(100, player.getScore());
        player.resetScore();
        assertEquals(0, player.getScore());
    }
}
