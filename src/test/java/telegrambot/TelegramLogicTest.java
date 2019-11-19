package tests.telegrambot;

import static org.junit.jupiter.api.Assertions.*;
import chatBote.TelegramBotLogic;
import chatBote.TelegramMesData;
import org.junit.jupiter.api.Test;
import tasks_extractor.QuizTasksExtractor;
import java.util.Arrays;
import java.util.List;

public class TelegramLogicTest {
    @Test
    public void createOneGameLogic() {
        FakeBot tb = new FakeBot();
        QuizTasksExtractor extractor = new QuizTasksExtractor("test_files/good");
        TelegramBotLogic logic = new TelegramBotLogic(tb, extractor);
        logic.objectModified(new TelegramMesData("a", "1", "test"));
        List<String> expectedKeys = Arrays.asList("1");
        assertEquals(expectedKeys.toString(), logic.subscribers.keySet().toString());
    }

    @Test
    public void createSeveralGamesLogic() {
        FakeBot tb = new FakeBot();
        QuizTasksExtractor extractor = new QuizTasksExtractor("test_files/good");
        TelegramBotLogic logic = new TelegramBotLogic(tb, extractor);
        logic.objectModified(new TelegramMesData("a", "1", "test"));
        logic.objectModified(new TelegramMesData("a", "111", "test"));
        List<String> expectedKeys = Arrays.asList("1", "111");
        assertEquals(expectedKeys.toString(), logic.subscribers.keySet().toString());
    }

    @Test
    public void gamesDoseNotRepeated() {
        FakeBot tb = new FakeBot();
        QuizTasksExtractor extractor = new QuizTasksExtractor("test_files/good");
        TelegramBotLogic logic = new TelegramBotLogic(tb, extractor);
        logic.objectModified(new TelegramMesData("a", "1", "test"));
        logic.objectModified(new TelegramMesData("a", "1", "test"));
        logic.objectModified(new TelegramMesData("a", "1", "test"));
        logic.objectModified(new TelegramMesData("a", "1", "test"));
        logic.objectModified(new TelegramMesData("a", "1", "test"));
        List<String> expectedKeys = Arrays.asList("1");
        assertEquals(expectedKeys.toString(), logic.subscribers.keySet().toString());
    }
}
