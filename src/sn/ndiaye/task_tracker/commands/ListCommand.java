package sn.ndiaye.task_tracker.commands;

import sn.ndiaye.task_tracker.Task;
import sn.ndiaye.task_tracker.TaskManager;
import sn.ndiaye.task_tracker.TaskStatus;
import java.util.Arrays;
import java.util.List;

class ListCommand implements Command{
    private final TaskManager taskManager;

    ListCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public CommandResult execute(String[] args) {
        var error = ArgsValidator.validateSize(args, 0, 1);
        if (error.isPresent()) return error.get();

        if (args.length == 0)
            return getResultWithFormattedTasks(taskManager.getAllTasks());

        var arg = args[0];
        var statusRegex = getStatusRegex();
        if (!arg.matches("(" + statusRegex + ")"))
            return new CommandResult("Error: Unknown status");
        var tasks = taskManager.getTasksWithStatus(
                TaskStatus.valueOf(arg.toUpperCase()
                        .replace("-", "_"))
        );
        return getResultWithFormattedTasks(tasks);
    }

    // Cli commands status are in this form: xx-xx-xx
    // TaskStatus are in this form XX_XX_XX
    private static String getStatusRegex() {
        var status = TaskStatus.values();
        return Arrays.stream(status)
                .map(stat -> stat.toString().toLowerCase()
                        .replace("_", "-"))
                .reduce((stat1, stat2) -> stat1 + "|" + stat2)
                .orElse("");
    }

    private CommandResult getResultWithFormattedTasks(List<Task> tasks) {
        var formattedResult =  tasks.stream()
                .map(Task::toString)
                .reduce((s1, s2) -> s1 + "\n" + s2)
                .orElse("No tasks");
        return new CommandResult("Task fetched with success", formattedResult);
    }
}
