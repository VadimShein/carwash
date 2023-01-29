package ru.project.carwash.service;

import org.springframework.stereotype.Service;
import ru.project.carwash.entity.Employment;
import ru.project.carwash.entity.Task;
import ru.project.carwash.entity.TimeLeftResponse;
import ru.project.carwash.repository.EmploymentRepository;
import ru.project.carwash.repository.TaskRepository;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service("TaskServiceImpl")
public class TaskServiceImpl implements TaskService {
    private final EmploymentRepository employmentRepository;
    private final TaskRepository taskRepository;
    private static final String WAITING = "waiting";

    public TaskServiceImpl(EmploymentRepository employmentRepository,
                           TaskRepository taskRepository) {
        this.employmentRepository = employmentRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public Task addTask(Task task) {
        Optional<Employment> employment = employmentRepository.findById(task.getEmployment().getId());
        if (employment.isPresent()) {
            LocalDateTime finishTime = task.getStartTime()
                    .plusHours(employment.get().getDuration().getHour())
                    .plusMinutes(employment.get().getDuration().getMinute())
                    .plusSeconds(employment.get().getDuration().getSecond());
            task.setFinishTime(finishTime);
            task.setStatus(WAITING);
            taskRepository.save(task);
            return task;
        }
        return new Task();
    }

    @Override
    public TimeLeftResponse getTimeLeft(int taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime timeLeft = task.get().getStartTime()
                    .minusMonths(now.getMonthValue())
                    .minusDays(now.getDayOfMonth())
                    .minusHours(now.getHour())
                    .minusMinutes(now.getMinute())
                    .minusSeconds(now.getSecond());
            long days = ChronoUnit.DAYS.between(now, task.get().getStartTime());
            String answer = "Time left: " + days + " days and  " + timeLeft.getHour() + ":" + timeLeft.getMinute() + ":" + timeLeft.getSecond();
            return new TimeLeftResponse(task.get().getId(), task.get().getUser().getId(),
                    task.get().getEmployment().getId(), answer, task.get().getStatus());
        }
        return new TimeLeftResponse();
    }
}
