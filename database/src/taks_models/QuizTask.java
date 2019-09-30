package taks_models;

import java.util.List;

public class QuizTask implements Task {
    public final String question;
    public final String correctAnswer;
    public final List<String> incorrectAnswers;

    public QuizTask(String question, String correctAnswer, List<String> incorrectAnswers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }
}
