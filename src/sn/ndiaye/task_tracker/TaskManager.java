package sn.ndiaye.task_tracker;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {

    public void addTask(Task task) {
        var tasks = new ArrayList<>(loadTasks());
        if (tasks.isEmpty())
            task.setId(1L);
        else
            task.setId(tasks.getLast().getId() + 1);
        tasks.add(task);
        saveTasks(tasks);
    }

    public List<Task> getTasks() {
        return loadTasks();
    }

    public void updateTask() {}

    public boolean deleteTask(Long id) {
        var tasks = new ArrayList<>(loadTasks());
        var originalCount = tasks.size();
        tasks.removeIf(task -> id.equals(task.getId()));
        var isDeleted = tasks.size() < originalCount;
        if (isDeleted)
            saveTasks(tasks);
        return isDeleted;
    }

    private List<Task> loadTasks() {
        var file = new File("tasks.json");
        String json;
        try (Scanner sc = new Scanner(file)) {
            json = sc.nextLine();
            return JsonParser.toObjects(json, Task.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveTasks(List<Task> tasks) {
        try {
            var fileWriter = new FileWriter("tasks.json");
            var json = JsonParser.toJsons(tasks);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
