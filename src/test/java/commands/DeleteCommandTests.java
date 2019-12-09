package commands;

import static org.junit.jupiter.api.Assertions.*;

import chatBot.TelegramBotLogic;
import chatBot.TelegramMesData;
import logic.FakeWriter;
import org.junit.jupiter.api.Test;
import tasks_extractor.QuizTasksExtractor;
import telegrambot.FakeBot;

public class DeleteCommandTests {
    @Test
    void commandDeleteTwoAnswersTest() {
        FakeTelegramWriterFactory writerFactory = new FakeTelegramWriterFactory();
        TelegramBotLogic botLogic = new TelegramBotLogic(new FakeBot(), new QuizTasksExtractor("src/test/test_files/good"),
                writerFactory);
        botLogic.objectModified(new TelegramMesData("name", "0", "/start"));
        FakeWriter writer = writerFactory.getWriter();
        Integer taskLineLength = writer.output.split("\n").length;
        assertEquals(5, taskLineLength);
        botLogic.objectModified(new TelegramMesData("name", "0", "/del"));
        writer = writerFactory.getWriter();
        taskLineLength = writer.output.split("\n").length;
        assertEquals(3, taskLineLength);
    }

    @Test
    void userCanNotUseHintTwiceTest() {
        FakeTelegramWriterFactory writerFactory = new FakeTelegramWriterFactory();
        TelegramBotLogic botLogic = new TelegramBotLogic(new FakeBot(), new QuizTasksExtractor("src/test/test_files/good"),
                writerFactory);
        botLogic.objectModified(new TelegramMesData("name", "0", "/start"));
        botLogic.objectModified(new TelegramMesData("name", "0", "/del"));
        botLogic.objectModified(new TelegramMesData("name", "0", "/del"));
        FakeWriter writer = writerFactory.getWriter();
        assertEquals("Вы уже использовали эту возможность", writer.output);
    }

    @Test
    void tryManyTimeToUseHintTest() {
        FakeTelegramWriterFactory writerFactory = new FakeTelegramWriterFactory();
        TelegramBotLogic botLogic = new TelegramBotLogic(new FakeBot(), new QuizTasksExtractor("src/test/test_files/good"),
                writerFactory);
        botLogic.objectModified(new TelegramMesData("name", "0", "/start"));
        for (Integer i = 0; i < 100; i++) {
            botLogic.objectModified(new TelegramMesData("name", "0", "/del"));
        }
        FakeWriter writer = writerFactory.getWriter();
        assertEquals("Вы уже использовали эту возможность", writer.output);
    }
}
