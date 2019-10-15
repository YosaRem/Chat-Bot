package tasks_extractor;

import taks_models.Task;

import java.io.IOException;

public interface Extractor {
    Task getRandomTask() throws IOException;
}
