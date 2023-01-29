package ru.project.carwash.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "StartTime must be non null")
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "finish_time")
    private LocalDateTime finishTime;
    @Size(min = 2, message = "Status must be more than two characters")
    private String status;
    @ManyToOne
    private User user;
    @ManyToOne
    private Employment employment;

    public Task() {
    }

    public Task(LocalDateTime startTime, LocalDateTime finishTime, String status, User user, Employment employment) {
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.status = status;
        this.user = user;
        this.employment = employment;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Employment getEmployment() {
        return employment;
    }

    public void setEmployment(Employment employment) {
        this.employment = employment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return id == task.id
                && Objects.equals(startTime, task.startTime)
                && Objects.equals(finishTime, task.finishTime)
                && Objects.equals(status, task.status)
                && Objects.equals(user, task.user)
                && Objects.equals(employment, task.employment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, finishTime, status, user, employment);
    }

    @Override
    public String toString() {
        return "Task{"
                + "id=" + id
                + ", startTime=" + startTime
                + ", finishTime=" + finishTime
                + ", status='" + status + '\''
                + ", user=" + user
                + ", employment=" + employment
                + '}';
    }
}
