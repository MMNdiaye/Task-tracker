package sn.ndiaye.task_tracker.commands;

import java.util.Optional;
import java.util.function.Supplier;

class ArgsValidator {

    static Optional<CommandResult> validateSize(String[] args, int minSize, int maxSize) {
        if (args.length < minSize)
            return Optional.of(CommandResult.missingArgs());
        if (args.length > maxSize)
            return Optional.of(CommandResult.tooManyArgs());
        return Optional.empty();
    }

    static Optional<CommandResult> validateId(String id) {
        try {
            Long.valueOf(id);
        } catch (NumberFormatException e) {
            return Optional.of(CommandResult.notId());
        }
        return Optional.empty();
    }

    static Optional<CommandResult> firstError(Supplier<Optional<CommandResult>>... checks) {
        for (var check : checks) {
            var error = check.get();
            if (error.isPresent())
                return error;
        }
        return Optional.empty();
    }
}
