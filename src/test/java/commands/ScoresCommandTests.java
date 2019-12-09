package commands;

import static org.junit.jupiter.api.Assertions.*;

import chatBot.TelegramBotLogic;
import chatBot.TelegramMesData;
import logic.FakeWriter;
import org.junit.jupiter.api.Test;
import tasks_extractor.QuizTasksExtractor;
import telegrambot.FakeBot;

public class ScoresCommandTests {
    @Test
    void zeroPointsTest() {
        FakeTelegramWriterFactory writerFactory = new FakeTelegramWriterFactory();
        TelegramBotLogic botLogic = new TelegramBotLogic(new FakeBot(), new QuizTasksExtractor("src/test/test_files/good"),
                writerFactory);
        botLogic.objectModified(new TelegramMesData("name", "0", "/start"));
        botLogic.objectModified(new TelegramMesData("name", "0", "/scores"));
        FakeWriter writer = writerFactory.getWriter();
        String[] lines = writer.output.split("\n");
        assertEquals("name", lines[0].split(" ")[2]);
        assertEquals("0", lines[1].split(" ")[3]);
        assertEquals("0", lines[2].split(" ")[3]);
    }

    @Test
    void firstLevelTest() {
        FakeTelegramWriterFactory writerFactory = new FakeTelegramWriterFactory();
        TelegramBotLogic botLogic = new TelegramBotLogic(new FakeBot(), new QuizTasksExtractor("src/test/test_files/good"),
                writerFactory);
        botLogic.objectModified(new TelegramMesData("name", "0", "/start"));
        FakeWriter writer = writerFactory.getWriter();
        String[] lines = writer.output.split("\n");
        String correctAnswer = "";
        for (String line: lines) {
            if (line.contains("CorrectAnswer")) {
                correctAnswer = line.substring(0, 1);
            }
        }
        botLogic.objectModified(new TelegramMesData("name", "0", correctAnswer));
        botLogic.objectModified(new TelegramMesData("name", "0", "/scores"));
        writer = writerFactory.getWriter();
        lines = writer.output.split("\n");
        assertEquals("1", lines[1].split(" ")[3]);
        assertEquals("500", lines[2].split(" ")[3]);
    }
}
