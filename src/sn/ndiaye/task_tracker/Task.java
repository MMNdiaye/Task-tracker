package sn.ndiaye.task_tracker;

import java.time.LocalDate;

public class Task {
    private Long id;
    private String name;
    private String description;
    private TaskStatus status;
    private LocalDate createdAt;
    private LocalDate modifiedAt;

    public Task() {

    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
