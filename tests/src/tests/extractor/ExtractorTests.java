package tests.extractor;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import taks_models.QuizTask;

import tasks_extractor.QuizTasksExtractor;

import static org.junit.jupiter.api.Assertions.*;

class ExtractorTests {
    private static final String goodPath = "test_files/good";
    private static final String badPath = "test_files/bad";

    @Test
    void readingFromCorrectFile() throws IOException {
        QuizTasksExtractor qt = new QuizTasksExtractor(goodPath);
        QuizTask task = (QuizTask) qt.getRandomTaskConsiderLevel(1);
        String[] mustAnsw = new String[]{"answ1", "answ2", "answ3", "correctAnswer"};
        assertEquals(task.getRightAnswer(), "correctAnswer");
        assertTrue(task.getOptions().values().containsAll(Arrays.asList(mustAnsw)));
    }

    @Test
    void moreLinesInFile() throws IOException {
        QuizTasksExtractor qt = new QuizTasksExtractor("test_files/moreLines");
        QuizTask task = (QuizTask) qt.getRandomTaskConsiderLevel(1);
        assertEquals(task.getRightAnswer(), "correctAnswer");
    }
}
