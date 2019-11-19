package taks_models;

import java.util.Map;

public class QuizTaskValue {
    private final String description;

    public QuizTaskValue(QuizTask quizTask) {
        Map<Integer, String> options = quizTask.getOptions();
        StringBuilder sb = new StringBuilder();
        sb.append(quizTask.getQuestion());
        for (int i = 1; i <= options.size(); i++) {
            sb.append("\n");
            sb.append(i);
            sb.append(": ");
            sb.append(options.get(i));
        }
        this.description = sb.toString();
    }

    public String getDescription() {
        return description;
    }
}
