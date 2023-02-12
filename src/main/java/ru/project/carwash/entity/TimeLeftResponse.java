package ru.project.carwash.entity;

import java.util.Objects;

public class TimeLeftResponse {
    private int taskId;
    private int userId;
    private int employmentId;
    private String timeLeft;
    private String status;

    public TimeLeftResponse() {
    }

    public TimeLeftResponse(int taskId, int userId, int employmentId, String timeLeft, String status) {
        this.taskId = taskId;
        this.userId = userId;
        this.employmentId = employmentId;
        this.timeLeft = timeLeft;
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
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

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeLeftResponse that = (TimeLeftResponse) o;
        return taskId == that.taskId
                && userId == that.userId
                && employmentId == that.employmentId
                && Objects.equals(timeLeft, that.timeLeft)
                && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, userId, employmentId, timeLeft, status);
    }
}
