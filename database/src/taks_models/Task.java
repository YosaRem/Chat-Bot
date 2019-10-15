package taks_models;

import java.util.Map;

public interface Task {
    String getRightAnswer();
    boolean checkAnswer(String inputtedValue);
    Map<Integer, String> mix();
}
