package sn.ndiaye.task_tracker;

import sn.ndiaye.task_tracker.commands.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class CommandLineInterface {
    private final Scanner scanner;
    private final TaskManager taskManager;

    public CommandLineInterface(Scanner scanner, TaskManager taskManager) {
        this.scanner = scanner;
        this.taskManager = taskManager;
    }

    public void start() {
        System.out.println("Welcome to Task Tracker. What do you want to do?");
        while (true) {
            System.out.print("> ");
            var commandLine = scanner.nextLine();
            if (commandLine.isBlank())
                continue;
            var commandParts = parseCommand(commandLine);
            var command = Command.generate(commandParts[0], taskManager);
            var args = Arrays.stream(commandParts)
                    .skip(1)
                    .toArray(String[]::new);
            var result = command.execute(args);
            System.out.println(result.getMessage());
            if (result.getContent() != null)
                System.out.println(result.getContent());
            if (command.isTerminal())
                return;
        }
    }

    // Command syntax: action [args]
    // Multi word arg syntax: "****  ****"
    // The spaces inside quotes are not to separate command and to use inner quotes you escape the quote: \"
    private String[] parseCommand(String command) {
        var parts = new ArrayList<String>();
        var isInsideQuotes = false;
        var currentToken = new StringBuilder();
        for (Character character : command.toCharArray()) {
            if (Character.isWhitespace(character) && !isInsideQuotes)
                currentToken = addPartAndResetToken(currentToken, parts);

            else if (character.equals('"')) {
                var isEscaped = checkEscapeFromToken(currentToken);
                if (isEscaped)
                    currentToken.append(character);
                else if (!isInsideQuotes)
                    isInsideQuotes = true;
                else {
                    isInsideQuotes = false;
                    parts.add(currentToken.toString());
                    currentToken = new StringBuilder();
                }
            }

            else
                currentToken.append(character);
        }
        if (!currentToken.isEmpty()) parts.add(currentToken.toString());
        return parts.toArray(String[]::new);
    }

    private static StringBuilder addPartAndResetToken(StringBuilder token, ArrayList<String> parts) {
        if (!token.isEmpty()) {
            var previousCharacterIndex = token.length() - 1;
            var previousCharacter = token.substring(previousCharacterIndex);
            if (!previousCharacter.equals(" ")) {
                parts.add(token.toString());
                token = new StringBuilder();
            }
        }
        return token;
    }

    private static boolean checkEscapeFromToken(StringBuilder token) {
        if (!token.isEmpty()) {
            var previousCharacterIndex = token.length() - 1;
            var previousCharacter = token.substring(previousCharacterIndex);
            return previousCharacter.equals("\\");
        }
        return false;
    }
}
