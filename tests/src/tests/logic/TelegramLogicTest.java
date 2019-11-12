package tests.logic;

import static org.junit.jupiter.api.Assertions.*;

import chatBote.TelegramBot;
import chatBote.TelegramBotLogic;
import chatBote.TelegramMesData;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.Update;
import tasks_extractor.Extractor;
import tasks_extractor.QuizTasksExtractor;

public class TelegramLogicTest {
    @Test
    public void createLogic() {
        FakeBot tb = new FakeBot();
        tb.onUpdateReceived(new FakeUpdate("text", "112", "Test1"));
        QuizTasksExtractor extractor = new QuizTasksExtractor("test_files/good");
        TelegramBotLogic logic = new TelegramBotLogic(tb, extractor);
        logic.objectModified(new TelegramMesData("a", "b", "c"));
        System.out.println(tb.ready);
    }
}
