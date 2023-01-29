package ru.project.carwash.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "employments")
public class Employment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min = 2, message = "Password must be more than two characters")
    private String name;
    @NotNull(message = "Duration must be non null")
    private LocalTime duration;

    public Employment() {
    }

    public Employment(String name, LocalTime duration) {
        this.name = name;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employment that = (Employment) o;
        return id == that.id
                && name.equals(that.name)
                && duration.equals(that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration);
    }

    @Override
    public String toString() {
        return "Employment{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", duration=" + duration
                + '}';
    }
}
