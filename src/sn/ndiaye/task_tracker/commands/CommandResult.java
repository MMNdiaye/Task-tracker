package sn.ndiaye.task_tracker.commands;

public class CommandResult {
    private final String message;
    private Object content;

    CommandResult(String message) {
        this.message = message;
    }

    CommandResult(String message, Object content) {
        this.message = message;
        this.content = content;
    }

    static CommandResult missingArgs() {
        return new CommandResult("Error: Missing argument(s)");
    }

    static CommandResult tooManyArgs() {
        return new CommandResult("Error: Too many argument(s)");
    }

    static CommandResult missingId() {
        return new CommandResult("Error: This id doesn't exist");
    }

    static CommandResult notId() {
        return new CommandResult("Error: Not a numeric id");
    }

    public String getMessage() {
        return message;
    }

    public Object getContent() {
        return content;
    }
}
