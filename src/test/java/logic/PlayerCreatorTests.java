package logic;

import static org.junit.jupiter.api.Assertions.*;

import game.PlayerCreator;
import game.Player;
import org.junit.jupiter.api.Test;
import publisher_subscriber.ISubscriber;

public class PlayerCreatorTests {
    @Test
    void createPlayerTest() {
        FakeReader reader = new FakeReader();
        reader.subscribe(new ISubscriber() {
            @Override
            public void objectModified(Object data) {
            }
        });
        PlayerCreator creator = new PlayerCreator(reader, new FakeWriter());
        Player player = creator.cratePlayerFromInput();
        assertEquals(reader.getInput(), player.getName());
        assertEquals(0, player.getScore());
    }
}
