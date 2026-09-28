package sn.ndiaye.task_tracker.commands;

import sn.ndiaye.task_tracker.TaskManager;

class UpdateCommand implements Command{
    private TaskManager taskManager;

    UpdateCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public CommandResult execute(String[] args) {
        if (args.length < 2)
            return new CommandResult("Error: Missing arguments");
        if (args.length > 2)
            return new CommandResult("Error: Too much arguments");
        var id = args[0];
        if (!id.matches("[0-9]+"))
            return new CommandResult("Error: Not a numeric id");
        var newName = args[1];
        var isUpdated = taskManager.updateTask(Long.valueOf(id), newName);
        if (isUpdated)
            return new CommandResult("Updated task with id " + id + " with success");
        else
            return new CommandResult("No task with id " + id + " to update");
    }
}
