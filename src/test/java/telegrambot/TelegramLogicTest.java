package telegrambot;

import static org.junit.jupiter.api.Assertions.*;

import chatBot.TelegramBotLogic;
import chatBot.TelegramMesData;
import commands.FakeTelegramWriterFactory;
import org.junit.jupiter.api.Test;
import tasks_extractor.QuizTasksExtractor;


public class TelegramLogicTest {
    @Test
    public void createOneGameLogic() {
        FakeBot tb = new FakeBot();
        FakeTelegramWriterFactory writerFactory = new FakeTelegramWriterFactory();
        QuizTasksExtractor extractor = new QuizTasksExtractor("src/test/test_files/good");
        TelegramBotLogic logic = new TelegramBotLogic(tb, extractor, writerFactory);
        logic.objectModified(new TelegramMesData("a", "1", "test"));
        assertEquals("1", tb.lastChatId);
        assertEquals("Question 0", tb.text.split("\n")[0]);
    }

    @Test
    public void createSeveralGamesLogic() {
        FakeBot tb = new FakeBot();
        FakeTelegramWriterFactory writerFactory = new FakeTelegramWriterFactory();
        QuizTasksExtractor extractor = new QuizTasksExtractor("src/test/test_files/good");
        TelegramBotLogic logic = new TelegramBotLogic(tb, extractor, writerFactory);
        logic.objectModified(new TelegramMesData("a", "1", "test"));
        assertEquals("1", tb.lastChatId);
        logic.objectModified(new TelegramMesData("a", "111", "test"));
        assertEquals("111", tb.lastChatId);
        assertEquals("Question 0", tb.text.split("\n")[0]);
    }
}
