package sn.ndiaye.task_tracker;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {
    private Long id;
    private String name;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    public Task() {

    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.status = TaskStatus.TODO;
        this.createdAt = LocalDateTime.now();
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    @Override
    public String toString() {
        return String.format("{Id: %d, task: %s, description: %s, status: %s, created at: %s, " +
                "last modified at: %s}", id, name, description, status, createdAt, lastModifiedAt);
    }


}
