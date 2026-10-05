package sn.ndiaye.task_tracker.commands;

import sn.ndiaye.task_tracker.TaskManager;

class DeleteCommand implements Command{
    private final TaskManager taskManager;

    DeleteCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public CommandResult execute(String[] args) {
        var error = ArgsValidator.firstError(
                () -> ArgsValidator.validateSize(args, 1, 1),
                () -> ArgsValidator.validateId(args[0])
        );
        if (error.isPresent()) return error.get();
        var isDeleted = taskManager.deleteTask(Long.valueOf(args[0]));
        if (isDeleted)
            return new CommandResult("Task with id " + args[0] + " is successfully deleted");
        else
            return CommandResult.missingId();
    }
}
