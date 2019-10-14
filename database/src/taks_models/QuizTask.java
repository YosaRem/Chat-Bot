package taks_models;

import java.util.*;

public class QuizTask implements Task {
    private final String question;
    private final String correctAnswer;
    private final List<String> incorrectAnswers;
    private Map<Integer, String> options;

    public QuizTask(String question, String correctAnswer, List<String> incorrectAnswers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
        this.options = mix();
    }

    @Override
    public String getRightAnswer() {
        return correctAnswer;
    }

    @Override
    public boolean checkAnswer(String inputtedValue) {
        return options.get(Integer.parseInt(inputtedValue)).equals(correctAnswer);
    }

    public Map<Integer, String> getOptions() {
        return options;
    }

    public String getQuestion() {
        return question;
    }

    @Override
    public Map<Integer, String> mix() {
        Map<Integer, String> result = new HashMap<>();
        ArrayList<String> incAnswers = new ArrayList<>(incorrectAnswers);
        incAnswers.add(correctAnswer);
        Collections.shuffle(incAnswers);
        for (int i = 1; i < 5; i++) {
            result.put(i, incAnswers.get(i - 1));
        }
        return result;
    }

    @Override
    public String getTaskToString() {
        return new QuizTaskValue(this).getDescription();
    }
}
