package daggertutorial;

import java.util.ArrayDeque;
import java.util.Deque;

import javax.inject.Inject;
import javax.inject.Singleton;

import daggertutorial.Command.Result;
import daggertutorial.Command.Status;

@Singleton
final class CommandProcessor {
    private final Deque<CommandRouter> commandRouterStack = new ArrayDeque<>();

    @Inject
    CommandProcessor(CommandRouter firstCommandRouter) {
        commandRouterStack.push(firstCommandRouter);
    }

    Status process(String input) {
        Result result = commandRouterStack.peek().route(input);
        if (result.status().equals(Status.INPUT_COMPLETED)) {
            commandRouterStack.pop();
            return commandRouterStack.isEmpty() ? Status.INPUT_COMPLETED : Status.HANDLED;
        }

        result.nestedCommandRouter().ifPresent(commandRouterStack::push);
        return result.status();
        
    }
}
