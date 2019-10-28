package tasks_extractor;

import game.Level;
import taks_models.Task;

import java.io.IOException;

public interface Extractor {
    Task getRandomTask(Level level) throws IOException;
}
