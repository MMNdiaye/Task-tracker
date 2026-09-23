package sn.ndiaye.task_tracker;

public interface Command {

    CommandResult execute(String[] args);

    static Command generate(String command, TaskManager taskManager) {
        return switch (command) {
            case "add" -> new AddCommand(taskManager);
            case "list" -> new ListCommand(taskManager);
            case "delete" -> new DeleteCommand(taskManager);
            case "exit" -> new ExitCommand();
            default -> new NoCommand();
        };
    }
}
