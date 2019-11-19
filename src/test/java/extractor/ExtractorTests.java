package extractor;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import taks_models.QuizTask;

import tasks_extractor.QuizTasksExtractor;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

class ExtractorTests {
    private static final String goodPath = "src/test/test_files/good";

    @Test
    void readingFromCorrectFile() throws IOException {
        QuizTasksExtractor qt = new QuizTasksExtractor(goodPath);
        QuizTask task = (QuizTask) qt.getRandomTaskConsiderLevel(0);
        String[] mustAnswer = new String[]{"wrong 0", "wrong 0", "wrong 0", "CorrectAnswer 0"};
        assertEquals(task.getRightAnswer(), "CorrectAnswer 0");
        assertTrue(task.getOptions().values().containsAll(Arrays.asList(mustAnswer)));
    }

    @Test
    void moreLinesInFile() throws IOException {
        QuizTasksExtractor qt = new QuizTasksExtractor("src/test/test_files/moreLines");
        QuizTask task = (QuizTask) qt.getRandomTaskConsiderLevel(0);
        assertEquals("correctAnswer", task.getRightAnswer());
        assertEquals(4, task.getOptions().size());
    }
}
