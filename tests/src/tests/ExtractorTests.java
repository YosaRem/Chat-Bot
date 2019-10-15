package tests;

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
    void readingFromCorrectFile() {
        QuizTasksExtractor qt = new QuizTasksExtractor(goodPath);
        QuizTask task = (QuizTask) qt.getRandomTask();
        String[] mustAnsw = new String[]{"answ1", "answ2", "answ3", "correctAnswer"};
        assertEquals(task.getRightAnswer(), "correctAnswer");
        assertTrue(task.getOptions().values().containsAll(Arrays.asList(mustAnsw)));
    }

    @Test
    void moreLinesInFile() {
        QuizTasksExtractor qt = new QuizTasksExtractor("test_files/moreLines");
        QuizTask task = (QuizTask) qt.getRandomTask();
        assertEquals(task.getRightAnswer(), "correctAnswer");
    }

    @Test
    void emptyFile() {
        QuizTasksExtractor qt = new QuizTasksExtractor(badPath);
        Assertions.assertThrows(IndexOutOfBoundsException.class, qt::getRandomTask);
    }
}
