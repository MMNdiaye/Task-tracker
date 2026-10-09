package sn.ndiaye.task_tracker.commands;

import sn.ndiaye.task_tracker.TaskManager;
import sn.ndiaye.task_tracker.TaskStatus;

class MarkStatusCommand implements Command {
    private final TaskManager taskManager;
    private final TaskStatus statusToMark;

    public MarkStatusCommand(TaskManager taskManager, TaskStatus statusToMark) {
        this.taskManager = taskManager;
        this.statusToMark = statusToMark;
    }

    @Override
    public CommandResult execute(String[] args) {
        var error = ArgsValidator.firstError(
                () -> ArgsValidator.validateSize(args, 1, 1),
                () -> ArgsValidator.validateId(args[0]));
        if (error.isPresent()) return error.get();
        var isMarkedAsStatus = taskManager.updateStatus(Long.valueOf(args[0]), statusToMark);
        if (isMarkedAsStatus)
            return new CommandResult("Task with id " + args[0] + " is marked as " + statusToMark);
        else
            return CommandResult.missingId();
    }
}
