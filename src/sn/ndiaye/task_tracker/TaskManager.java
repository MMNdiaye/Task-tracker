package sn.ndiaye.task_tracker;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

    public List<Task> getAllTasks() {
        return loadTasks();
    }

    public List<Task> getTasksWithStatus(TaskStatus taskStatus) {
        var tasks = loadTasks();
        if (taskStatus == null)
            return tasks;
        return tasks.stream()
                .filter(t -> taskStatus.equals(t.getStatus()))
                .toList();
    }

    public boolean updateTask(Long id, String newName) {
        var tasks = new ArrayList<>(loadTasks());
        for (var task : tasks) {
            if (id.equals(task.getId())) {
                task.setName(newName);
                task.setLastModifiedAt(LocalDateTime.now());
                saveTasks(tasks);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTask(Long id) {
        var tasks = new ArrayList<>(loadTasks());
        var originalCount = tasks.size();
        tasks.removeIf(task -> id.equals(task.getId()));
        var isDeleted = tasks.size() < originalCount;
        if (isDeleted)
            saveTasks(tasks);
        return isDeleted;
    }

    public boolean updateStatus(Long id, TaskStatus taskStatus) {
        var tasks = loadTasks();
        for (var task : tasks)
            if (id.equals(task.getId())) {
                task.setStatus(taskStatus);
                task.setLastModifiedAt(LocalDateTime.now());
                saveTasks(tasks);
                return true;
            }
        return false;
    }

    private List<Task> loadTasks() {
        var file = new File("tasks.json");
        String json;
        try (Scanner sc = new Scanner(file)) {
            json = sc.nextLine();
            return JsonParser.toObjects(json, Task.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchElementException e) {
            return new ArrayList<>();
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
