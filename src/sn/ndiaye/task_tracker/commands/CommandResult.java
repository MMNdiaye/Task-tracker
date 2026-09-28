package sn.ndiaye.task_tracker.commands;

public class CommandResult {
    private String message;
    private Object content;

    CommandResult(String message) {
        this.message = message;
    }

    CommandResult(String message, Object content) {
        this.message = message;
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public Object getContent() {
        return content;
    }
}
