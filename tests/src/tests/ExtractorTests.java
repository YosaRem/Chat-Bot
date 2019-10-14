package tests;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tasks_extractor.QuizTasksExtractor;


class ExtractorTests {
    private static final String goodPath = "test_files/good";
    private static final String badPath = "test_files/bad";

    @Test
    void missingFile() {
        QuizTasksExtractor qt = new QuizTasksExtractor(badPath);
        Assertions.assertThrows(FileNotFoundException.class, qt::getRandomTask);
    }
}
