package daggertutorial;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import daggertutorial.Command.Result;
import daggertutorial.Command.Status;

final class CommandRouter {
    private final Map<String, Command> commands;
    private final Outputter outputter;
    
    @Inject
    CommandRouter(Map<String, Command> commands, Outputter outputter) {
        this.commands = commands;
        this.outputter = outputter;
    }
    
    Result route(String input) {
        List<String> splitInput = split(input);
        if (splitInput.isEmpty()) {
            return invalidCommand(input);
        }

        String commandKey = splitInput.get(0);
        Command command = commands.get(commandKey);
        if (command == null) {
            return invalidCommand(input);
        }

        List<String> args = splitInput.subList(1, splitInput.size());
        Result result = command.handleInput(args);
        return result.status().equals(Status.INVALID) ? invalidCommand(input) : result;
    }

    private Result invalidCommand(String input) {
        outputter.output(String.format("couldn't understand \"%s\", please try again", input));
        return Result.invalid();
    }

    private static List<String> split(String input) {
        return Arrays.asList(input.trim().split("\\s+"));
    }
}
