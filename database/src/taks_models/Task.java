package taks_models;

import java.util.Map;

public interface Task {
    String getQuestion();
    String[] getTaskToString();
    String getRightAnswer();
    Boolean checkAnswer(String inputtedValue);
    Map<Integer, String> mix();
}
