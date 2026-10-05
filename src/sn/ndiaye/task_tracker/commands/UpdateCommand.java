package sn.ndiaye.task_tracker.commands;

import sn.ndiaye.task_tracker.TaskManager;

class UpdateCommand implements Command{
    private final TaskManager taskManager;

    UpdateCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public CommandResult execute(String[] args) {
        var error = ArgsValidator.firstError(
                () -> ArgsValidator.validateSize(args, 2, 2),
                () -> ArgsValidator.validateId(args[0]));
        if (error.isPresent()) return error.get();
        var id = args[0];
        var newName = args[1];
        var isUpdated = taskManager.updateTask(Long.valueOf(id), newName);
        if (isUpdated)
            return new CommandResult("Updated task with id " + id + " with success");
        else
            return new CommandResult("No task with id " + id + " to update");
    }
}
