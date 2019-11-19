package extractor;

import org.junit.jupiter.api.Test;
import taks_models.QuizTask;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class QuizTests {
    private static final String testPath = "src/main/resources";

    @Test
    void createQuizTask() {
        QuizTask task = getTask();
        assertEquals(task.getQuestion(), "question");
        assertEquals(task.getRightAnswer(), "correctAnswer");
    }

    @Test
    void checkAllAnswer() {
        Map<Integer, String> options = getTask().getOptions();
        ArrayList<String> answers = new ArrayList<>();
        for (var i = 1; i < 4; i++)
            answers.add("answ" + i);
        answers.add("correctAnswer");
        assertTrue(options.values().containsAll(answers));
        assertTrue(answers.containsAll(options.values()));
    }

    @Test
    void EqualsAnswersAfterMix() {
        QuizTask task = getTask();
        String[] answers1 = task.getOptions().values().toArray(new String[0]).clone();
        Collection<String> answers2 = task.mix().values();
        assertTrue(answers2.containsAll(Arrays.asList(answers1)));
        assertTrue(Arrays.asList(answers1).containsAll(answers2));
    }

    private QuizTask getTask() {
        ArrayList<String> answers = new ArrayList<>();
        for (var i = 1; i < 4; i++)
            answers.add("answ" + i);
        return new QuizTask("question", "correctAnswer", answers);
    }
}
