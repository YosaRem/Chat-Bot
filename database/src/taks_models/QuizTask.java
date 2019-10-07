package taks_models;

import java.lang.reflect.Array;
import java.security.KeyException;
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
    public String getQuestion() {
        return question;
    }

    @Override
    public String[] getTaskToString() {
        String[] answers = new String[options.size() + 1];
        answers[0] = question;
        for (Integer i = 1; i <= options.size(); i++) {
            answers[i] = i.toString() + ": " + options.get(i);
        }
        return answers;
    }

    @Override
    public String getRightAnswer() {
        return correctAnswer;
    }

    @Override
    public Boolean checkAnswer(String inputtedValue) {
        return options.get(Integer.parseInt(inputtedValue)).equals(correctAnswer);
    }

    @Override
    public Map<Integer, String> mix() {
        Map<Integer, String> result = new HashMap<>();
        List<String> incAnswers = new ArrayList<>(incorrectAnswers);
        incAnswers.add(correctAnswer);
        Collections.shuffle(incAnswers);
        for (int i = 1; i < 5; i++) {
            result.put(i, incAnswers.get(i - 1));
        }
        return result;
    }
}
