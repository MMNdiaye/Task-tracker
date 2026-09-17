package sn.ndiaye.task_tracker;

public interface Command {

    void execute(String[] args);

    static Command generate(String command, TaskManager taskManager) {
        if (command.equals("add"))
            return new AddCommand(taskManager);

        throw new IllegalArgumentException("Error: Unregistered command");
    }
}
