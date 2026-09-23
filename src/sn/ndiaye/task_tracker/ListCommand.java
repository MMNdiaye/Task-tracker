package sn.ndiaye.task_tracker;

public class ListCommand implements Command{
    public TaskManager taskManager;

    public ListCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public CommandResult execute(String[] args) {
        if (args.length != 0)
            throw new IllegalArgumentException("Error! Too much arguments");
        var tasks = taskManager.getTasks();
        return new CommandResult("Tasks fetched with success.",
                tasks);
    }
}
