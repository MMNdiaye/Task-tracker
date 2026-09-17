package sn.ndiaye.task_tracker;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {
    public void addTask(Task task) {
        var file = new File("tasks.json");
        String[] jsonStrings = null;
        try(Scanner sc = new Scanner(file)) {
            sc.useDelimiter("[\\[\\]]");
            var json = sc.next();
            jsonStrings = json.split(",");
        }catch (FileNotFoundException _) {
        }
        System.out.println(Arrays.toString(jsonStrings));
    }

    public void listTasks() {}

    public void updateTask() {}

    public void deleteTask() {}
}
