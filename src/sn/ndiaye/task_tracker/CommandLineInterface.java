package sn.ndiaye.task_tracker;

import java.util.Arrays;
import java.util.Scanner;

public class CommandLineInterface {
    private Scanner scanner;
    private TaskManager taskManager;

    public CommandLineInterface(Scanner scanner, TaskManager taskManager) {
        this.scanner = scanner;
        this.taskManager = taskManager;
    }

    public void start() {
        System.out.println("Welcome to Task Tracker. What do you want to do?");
        while (true) {
            System.out.print("> ");
            var commandLine = scanner.nextLine();
            var commandParts = commandLine.split("\\s+");
            var command = Command.generate(commandParts[0], taskManager);
            command.execute(Arrays.stream(commandParts).skip(1).toArray(String[]::new));
            return;
        }
    }
}
