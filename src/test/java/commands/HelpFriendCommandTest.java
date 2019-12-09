package commands;

import static org.junit.jupiter.api.Assertions.*;

import chatBot.TelegramBotLogic;
import chatBot.TelegramMesData;
import logic.FakeWriter;
import org.junit.jupiter.api.Test;
import tasks_extractor.QuizTasksExtractor;
import telegrambot.FakeBot;

public class HelpFriendCommandTest {
    @Test
    void zeroFriendsTest() {
        FakeTelegramWriterFactory writerFactory = new FakeTelegramWriterFactory();
        TelegramBotLogic botLogic = new TelegramBotLogic(new FakeBot(), new QuizTasksExtractor("src/test/test_files/good"),
                writerFactory);
        botLogic.objectModified(new TelegramMesData("name", "0", "/start"));
        botLogic.objectModified(new TelegramMesData("name", "0", "/resend"));
        FakeWriter writer = writerFactory.getWriter();
        assertEquals("В данный момент нет других пользователей.", writer.output);
    }

    @Test
    void NotZeroFriendsTest() {
        FakeTelegramWriterFactory writerFactory = new FakeTelegramWriterFactory();
        TelegramBotLogic botLogic = new TelegramBotLogic(new FakeBot(), new QuizTasksExtractor("src/test/test_files/good"),
                writerFactory);
        botLogic.objectModified(new TelegramMesData("name", "0", "/start"));
        botLogic.objectModified(new TelegramMesData("name1", "1", "/start"));
        botLogic.objectModified(new TelegramMesData("name", "0", "/resend"));
        FakeWriter writer = writerFactory.getWriter();
        assertEquals("Пользователи", writer.output);
    }
}
