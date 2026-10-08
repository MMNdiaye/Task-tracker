package sn.ndiaye.task_tracker;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TaskManager {
    private final String SAVEFILE_PATH;

    public TaskManager(String SAVEFILE_PATH) {
        this.SAVEFILE_PATH = SAVEFILE_PATH;
    }

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
        var tasks = loadTasks();
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
        for (int i = 0; i < tasks.size(); i++)
            if (id.equals(tasks.get(i).getId())) {
                tasks.remove(i);
                saveTasks(tasks);
                return true;
            }
        return false;
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
        var file = new File(SAVEFILE_PATH);
        try (Scanner sc = new Scanner(file)) {
            // Our json is all written on one line
            var json = sc.nextLine();
            return JsonParser.toObjects(json, Task.class);
        } catch (FileNotFoundException | NoSuchElementException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveTasks(List<Task> tasks) {
        var file = new File(SAVEFILE_PATH);
        try(var fileWriter = new FileWriter(file)){
            var json = JsonParser.toJsons(tasks);
            fileWriter.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
