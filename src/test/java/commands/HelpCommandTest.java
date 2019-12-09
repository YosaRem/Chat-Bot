package commands;

import static org.junit.jupiter.api.Assertions.*;

import chatBot.TelegramBotLogic;
import chatBot.TelegramMesData;
import game.Player;
import game.QuizGame;
import game.QuizLogic;
import logic.FakeWriter;
import org.junit.jupiter.api.Test;
import tasks_extractor.QuizTasksExtractor;
import telegrambot.FakeBot;

import java.util.HashMap;


public class HelpCommandTest {
    @Test
    void checkingHintsCountTest() {
        FakeTelegramWriterFactory writerFactory = new FakeTelegramWriterFactory();
        TelegramBotLogic botLogic = new TelegramBotLogic(new FakeBot(), new QuizTasksExtractor("src/test/test_files/good"),
                writerFactory);
        botLogic.objectModified(new TelegramMesData("name", "0", "/start"));
        botLogic.objectModified(new TelegramMesData("name", "0", "/help"));
        CommandConverter converter = new CommandConverter(
                new QuizLogic(
                        new FakeWriter(), new Player("name"), new QuizGame(new QuizTasksExtractor("src/test/tst_files/good"))
                ),
                new HashMap<>());
        converter.defineCommands();
        FakeWriter writer = writerFactory.getWriter();
        assertEquals(converter.getAllCommands().size() - 1, writer.output.split("\n").length);
    }
}
