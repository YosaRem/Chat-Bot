package tests.logic;

import static org.junit.jupiter.api.Assertions.*;

import game.PlayerCreator;
import game.Player;
import org.junit.jupiter.api.Test;

public class PlayerCreatorTests {
    @Test
    void createPlayerTest() {
        FakeReader reader = new FakeReader();
        PlayerCreator creator = new PlayerCreator(reader, new FakeWriter());
        Player player = creator.cratePlayerFromInput();
        assertEquals(reader.getInput(), player.getName());
        assertEquals(0, player.getScore());
    }
}
