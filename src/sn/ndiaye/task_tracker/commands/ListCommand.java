package sn.ndiaye.task_tracker.commands;

import sn.ndiaye.task_tracker.Task;
import sn.ndiaye.task_tracker.TaskManager;
import sn.ndiaye.task_tracker.TaskStatus;

import java.util.Arrays;
import java.util.List;

class ListCommand implements Command{
    public TaskManager taskManager;

    ListCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public CommandResult execute(String[] args) {
        List<Task> tasks = List.of();
        if (args.length > 1)
            return new CommandResult("Error: Too much arguments");

        if (args.length == 0)
            tasks = taskManager.getAllTasks();

        if (args.length == 1) {
            var arg = args[0];
            var status = TaskStatus.values();
            var statusRegex = Arrays.stream(status)
                    .map(stat -> stat.toString().toLowerCase()
                            .replace("_", "-"))
                    .reduce((stat1, stat2) -> stat1 + "|" + stat2)
                    .orElse("");

            if (!arg.matches("(" + statusRegex + ")"))
                return new CommandResult("Error: Unknown status");
            tasks = taskManager.getTasksWithStatus(
                    TaskStatus.valueOf(arg.toUpperCase()
                            .replace("-", "_"))
            );
        }

        return new CommandResult("Tasks fetched with success", tasks);
    }
}
