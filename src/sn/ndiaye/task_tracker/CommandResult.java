package sn.ndiaye.task_tracker;

public class CommandResult {
    private String message;
    private Object content;

    public CommandResult(String message) {
        this.message = message;
    }

    public CommandResult(String message, Object content) {
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
