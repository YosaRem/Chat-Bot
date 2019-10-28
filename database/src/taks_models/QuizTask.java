package taks_models;

import java.util.*;

public class QuizTask implements Task {
    private final String question;
    private final String correctAnswer;
    private final List<String> incorrectAnswers;
    private final Map<Integer, String> options;

    public QuizTask(String question, String correctAnswer, List<String> incorrectAnswers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
        this.options = mix();
    }

    public String getQuestion() {
        return question;
    }

    public Map<Integer, String> getOptions() {
        return options;
    }

    @Override
    public String getRightAnswer() {
        return correctAnswer;
    }

    @Override
    public boolean checkAnswer(String inputtedValue) {
        try {
            return options.get(Integer.parseInt(inputtedValue)).equals(correctAnswer);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public QuizTask deleteTwoIncorrectAnswer() {
        QuizTask task = new QuizTask(question,
                correctAnswer,
                incorrectAnswers.subList(0, incorrectAnswers.size() - 2)
        );
        return task;
    }

    private Map<Integer, String> mix() {
        Map<Integer, String> result = new HashMap<>();
        ArrayList<String> incAnswers = new ArrayList<>(incorrectAnswers);
        incAnswers.add(correctAnswer);
        Collections.shuffle(incAnswers);
        for (int i = 1; i <= incAnswers.size(); i++) {
            result.put(i, incAnswers.get(i - 1));
        }
        return result;
    }
}
