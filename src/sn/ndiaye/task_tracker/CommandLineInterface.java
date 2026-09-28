package sn.ndiaye.task_tracker;

import sn.ndiaye.task_tracker.commands.Command;
import sn.ndiaye.task_tracker.commands.ExitCommand;

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
            var commandParts = commandLine.split("\\s+(?=([^\"]*\"[^\"]*\"|[^\"]*$))");
            System.out.println(Arrays.toString(commandParts));
            var command = Command.generate(commandParts[0], taskManager);
            var args = Arrays.stream(commandParts).skip(1).toArray(String[]::new);
            var result = command.execute(args);
            System.out.println(result.getMessage());
            if (result.getContent() != null)
                System.out.println(result.getContent());
            if (command.getClass() == ExitCommand.class)
                return;
        }
    }
}
