package sn.ndiaye.task_tracker;

public class AddCommand implements Command{
    private TaskManager taskManager;

    public AddCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public CommandResult execute(String[] args) {
        if (args.length == 0)
            return new CommandResult("Error: Missing task name.");
        if (args.length > 2)
            return new CommandResult("Error: Too much arguments.");
        var name = args[0];
        var description = args.length == 2 ? args[1] : "";
        var task = new Task(name, description);
        taskManager.addTask(task);
            return new CommandResult("Added task " + name + " with success");
    }
}
