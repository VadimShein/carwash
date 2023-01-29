package ru.project.carwash.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class TaskDTO {
    private int id;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private String status;
    private int userId;
    private int employmentId;

    public TaskDTO() {
    }

    public TaskDTO(int id, LocalDateTime startTime, LocalDateTime finishTime, String status, int userId, int employmentId) {
        this.id = id;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.status = status;
        this.userId = userId;
        this.employmentId = employmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEmploymentId() {
        return employmentId;
    }

    public void setEmploymentId(int employmentId) {
        this.employmentId = employmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskDTO taskDTO = (TaskDTO) o;
        return id == taskDTO.id
                && userId == taskDTO.userId
                && employmentId == taskDTO.employmentId
                && Objects.equals(startTime, taskDTO.startTime)
                && Objects.equals(finishTime, taskDTO.finishTime)
                && Objects.equals(status, taskDTO.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, finishTime, status, userId, employmentId);
    }

    @Override
    public String toString() {
        return "TaskDTO{"
                + "id=" + id
                + ", startTime=" + startTime
                + ", finishTime=" + finishTime
                + ", status='" + status + '\''
                + ", userId=" + userId
                + ", employmentId=" + employmentId
                + '}';
    }
}
