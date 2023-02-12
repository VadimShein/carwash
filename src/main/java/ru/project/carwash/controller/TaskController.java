package ru.project.carwash.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @QueryMapping
    @GetMapping("/task/{taskId}")
    public TimeLeftResponse getTimeLeft(@Argument @PathVariable int taskId) {
        return taskService.getTimeLeft(taskId);
    }

    @MutationMapping
    @PostMapping("/task")
    public ResponseEntity<TaskDTO> addTask(@Argument @Valid @RequestBody Task task) {
        System.out.println(task.getUser().getUsername());
        return new ResponseEntity<>(taskService.addTask(task), HttpStatus.CREATED);
    }
}
