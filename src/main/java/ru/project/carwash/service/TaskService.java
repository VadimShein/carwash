package ru.project.carwash.service;

import ru.project.carwash.entity.Task;
import ru.project.carwash.entity.TaskDTO;
import ru.project.carwash.entity.TimeLeftResponse;

public interface TaskService {
    TaskDTO addTask(Task task);
    TimeLeftResponse getTimeLeft(int taskId);
}
