package sn.ndiaye.task_tracker;

import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final TaskManager TASK_MANAGER = new TaskManager();
    private static final CommandLineInterface CLI = new CommandLineInterface(SCANNER, TASK_MANAGER);

    public static void main(String[] args) {
        CLI.start();
    }
}
