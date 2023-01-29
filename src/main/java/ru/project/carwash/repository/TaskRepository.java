package ru.project.carwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.carwash.entity.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<Task> findById(int id);
    List<Task> findAllByUserId(int userId);
    List<Task> findAllByStartTimeLessThanAndFinishTimeGreaterThan(
            LocalDateTime start, LocalDateTime finish);
}
