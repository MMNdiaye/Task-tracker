package sn.ndiaye.task_tracker.commands;

import sn.ndiaye.task_tracker.Task;
import sn.ndiaye.task_tracker.TaskManager;

class AddCommand implements Command{
    private final TaskManager taskManager;

    AddCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public CommandResult execute(String[] args) {
        var error = ArgsValidator.validateSize(args, 1, 2);
        if (error.isPresent()) return error.get();
        var name = args[0];
        var description = args.length == 2 ? args[1] : "";
        var task = new Task(name, description);
        taskManager.addTask(task);
        return new CommandResult("Added new task with id: " + task.getId() + " with success");
    }
}
