package sn.ndiaye.task_tracker.commands;

import sn.ndiaye.task_tracker.TaskManager;
import sn.ndiaye.task_tracker.TaskStatus;

public interface Command {

    CommandResult execute(String[] args);

    static Command generate(String command, TaskManager taskManager) {
        return switch (command) {
            case "add" -> new AddCommand(taskManager);
            case "list" -> new ListCommand(taskManager);
            case "update" -> new UpdateCommand(taskManager);
            case "mark-done" -> new MarkStatusCommand(taskManager, TaskStatus.DONE);
            case "mark-in-progress" -> new MarkStatusCommand(taskManager, TaskStatus.IN_PROGRESS);
            case "delete" -> new DeleteCommand(taskManager);
            case "exit" -> new ExitCommand();
            default -> new NoCommand();
        };
    }

    default boolean isTerminal() {
        return false;
    }
}
