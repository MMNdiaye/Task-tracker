package sn.ndiaye.task_tracker;

public class Task {
    private Long id;
    private String name;
    private String description;

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
}
