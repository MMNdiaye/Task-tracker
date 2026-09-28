package sn.ndiaye.task_tracker.commands;

import sn.ndiaye.task_tracker.TaskManager;

class ListCommand implements Command{
    public TaskManager taskManager;

    ListCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public CommandResult execute(String[] args) {
        if (args.length != 0)
            return new CommandResult("Error! Too much arguments");
        var tasks = taskManager.getTasks();
        return new CommandResult("Tasks fetched with success.",
                tasks);
    }
}
