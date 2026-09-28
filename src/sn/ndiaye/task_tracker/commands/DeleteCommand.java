package sn.ndiaye.task_tracker.commands;

import sn.ndiaye.task_tracker.TaskManager;

class DeleteCommand implements Command{
    private TaskManager taskManager;

    DeleteCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public CommandResult execute(String[] args) {
        if (args.length != 1)
            return new CommandResult("Error: Too much arguments");
        var id = args[0];
        if (!id.matches("[0-9]+"))
            return new CommandResult("Error: Not a numeric id");
        var isDeleted = taskManager.deleteTask(Long.valueOf(id));
        if (isDeleted)
            return new CommandResult("Task with id " + args[0] + " is successfully deleted");
        else
            return new CommandResult("No task with this id to delete");
    }
}
