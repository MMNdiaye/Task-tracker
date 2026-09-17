package sn.ndiaye.task_tracker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final TaskManager TASK_MANAGER = new TaskManager();
    private static final CommandLineInterface CLI = new CommandLineInterface(SCANNER, TASK_MANAGER);

    public static void main(String[] args) {
        initFile();
        CLI.start();
    }

    public static void initFile() {
        var file = new File("tasks.json");
        try {
            if (file.createNewFile()) {
                var fileWriter = new FileWriter(file);
                fileWriter.write("[]");
                fileWriter.close();
            }
        }catch (IOException _) {

        }
    }
}
