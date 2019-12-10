package telegrambot;

import static org.junit.jupiter.api.Assertions.*;

import chatBot.TelegramBotLogic;
import chatBot.TelegramMesData;
import commands.FakeTelegramWriterFactory;
import logic.FakeWriter;
import org.junit.jupiter.api.Test;
import tasks_extractor.QuizTasksExtractor;


public class TelegramLogicTest {
    @Test
    public void createOneGameLogic() {
        FakeBot tb = new FakeBot();
        FakeTelegramWriterFactory writerFactory = new FakeTelegramWriterFactory();
        QuizTasksExtractor extractor = new QuizTasksExtractor("src/test/test_files/good");
        TelegramBotLogic logic = new TelegramBotLogic(tb, extractor, writerFactory);
        logic.objectModified(new TelegramMesData("a", "1", "/start"));
        logic.objectModified(new TelegramMesData("a", "1", "/scores"));
        FakeWriter writer = writerFactory.getWriter();
        String[] lines = writer.output.split("\n");
        assertEquals("a", lines[0].split(" ")[2]);
        assertEquals("0", lines[1].split(" ")[3]);
        assertEquals("0", lines[2].split(" ")[3]);;
    }

    @Test
    public void createSeveralGamesLogic() {
        FakeBot tb = new FakeBot();
        FakeTelegramWriterFactory writerFactory = new FakeTelegramWriterFactory();
        QuizTasksExtractor extractor = new QuizTasksExtractor("src/test/test_files/good");
        TelegramBotLogic logic = new TelegramBotLogic(tb, extractor, writerFactory);
        logic.objectModified(new TelegramMesData("a", "1", "/start"));
        logic.objectModified(new TelegramMesData("a", "1", "/scores"));
        FakeWriter writer = writerFactory.getWriter();
        String[] lines = writer.output.split("\n");
        assertEquals("a", lines[0].split(" ")[2]);
        logic.objectModified(new TelegramMesData("aaa", "111", "/start"));
        logic.objectModified(new TelegramMesData("aaa", "111", "/scores"));
        writer = writerFactory.getWriter();
        lines = writer.output.split("\n");
        assertEquals("aaa", lines[0].split(" ")[2]);
    }
}
