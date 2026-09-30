package sn.ndiaye.task_tracker.commands;

import sn.ndiaye.task_tracker.TaskManager;
import sn.ndiaye.task_tracker.TaskStatus;

public class MarkStatusCommand implements Command {
    private final TaskManager taskManager;
    private final TaskStatus statusToMark;

    public MarkStatusCommand(TaskManager taskManager, TaskStatus statusToMark) {
        this.taskManager = taskManager;
        this.statusToMark = statusToMark;
    }

    @Override
    public CommandResult execute(String[] args) {
        if (args.length < 1)
            return CommandResult.missingArgs();
        if (args.length > 1)
            return CommandResult.tooManyArgs();
        var idArg = args[0];
        if (!idArg.matches("[0-9]+"))
            return new CommandResult("Error: Not a id");
        var isMarkedAsDone = taskManager.updateStatus(Long.valueOf(idArg), statusToMark);
        if (isMarkedAsDone)
            return new CommandResult("Task with id " + idArg + " is marked as " + statusToMark);
        else
            return CommandResult.missingId();
    }
}
