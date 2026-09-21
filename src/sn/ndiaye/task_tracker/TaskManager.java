package sn.ndiaye.task_tracker;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    public void addTask(Task task) {
        var file = new File("tasks.json");
        List<Task> tasks;
        String json;
        try (Scanner sc = new Scanner(file)) {
            json = sc.nextLine();
            tasks = JsonParser.toObject(json, Task.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (tasks.isEmpty())
            task.setId(1L);
        else
            task.setId(tasks.getLast().getId() + 1);
        var jsonString = JsonParser.toJson(task);
        json = json.replaceFirst("]", ", ") + jsonString + "]";
        try {
            var fileWriter = new FileWriter("tasks.json");
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listTasks() {}

    public void updateTask() {}

    public void deleteTask() {}
}
