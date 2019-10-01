package tasks_extractor;

import taks_models.Task;

import java.util.List;

public interface Extractor {
    List<Task> getListAllTasks();
}
