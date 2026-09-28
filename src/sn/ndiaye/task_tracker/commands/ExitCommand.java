package sn.ndiaye.task_tracker.commands;

class ExitCommand implements Command {

    @Override
    public CommandResult execute(String[] args) {
        return new CommandResult("Thank you for using this application. Goodbye!");
    }
}
