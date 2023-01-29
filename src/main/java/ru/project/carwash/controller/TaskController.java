package ru.project.carwash.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.project.carwash.entity.Task;
import ru.project.carwash.entity.TaskDTO;
import ru.project.carwash.entity.TimeLeftResponse;
import ru.project.carwash.service.TaskService;

import javax.validation.Valid;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/task/{taskId}")
    public TimeLeftResponse getTimeLeft(@PathVariable String taskId) {
        return taskService.getTimeLeft(Integer.parseInt(taskId));
    }

    @PostMapping("/task")
    public ResponseEntity<TaskDTO> addTask(@Valid @RequestBody Task task) {
        if (task.getUser().getId() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "User id mustn't be 0");
        }
        if (task.getEmployment().getId() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Employment id mustn't be 0");
        }
        if (task.getStartTime() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Start time mustn't be null");
        }
        return new ResponseEntity<>(taskService.addTask(task), HttpStatus.CREATED);
    }
}
