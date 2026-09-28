package sn.ndiaye.task_tracker.commands;

class NoCommand implements Command{
    @Override
    public CommandResult execute(String[] args) {
        return new CommandResult("Error: unregistered command");
    }
}
