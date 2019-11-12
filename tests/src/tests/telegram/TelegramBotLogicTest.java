package tests.telegram;

import static org.junit.jupiter.api.Assertions.*;

import chatBote.TelegramBot;
import chatBote.TelegramBotLogic;
import chatBote.TelegramMesData;
import org.junit.jupiter.api.Test;
import tasks_extractor.QuizTasksExtractor;

import java.util.ArrayList;
import java.util.Set;

public class TelegramBotLogicTest {
    @Test
    public void createGameTest() {
        QuizTasksExtractor extractor = new QuizTasksExtractor("/test_files/good");
        TelegramBot tb = new TelegramBot();
        TelegramBotLogic tbl = new TelegramBotLogic(tb, extractor);
        tbl.objectModified(new TelegramMesData("Ilya", "0", "test"));
        Set<String> gamesID = tbl.getGamesID();
        ArrayList<String> expacted = new ArrayList<String>();
        expacted.add("0");
        assertArrayEquals(gamesID.toArray(), expacted.toArray());
    }

}
