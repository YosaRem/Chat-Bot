package tasks_extractor;

import game.QuizGame;
import taks_models.Task;

import java.io.IOException;

public interface Extractor {
    Task getRandomTaskConsiderLevel(int level) throws IOException;
}
