package dagger.tutorial;

import java.util.List;

import javax.inject.Inject;

class HelloWorldCommand implements Command {

    private final Outputter outputter;

    @Inject
    HelloWorldCommand(Outputter outputter) {
        this.outputter = outputter;
    }

    @Override
    public Status handleInput(List<String> input) {
        outputter.output("world!");
        if (!input.isEmpty()) {
            return Status.INVALID;
        }
        System.out.println("world!");
        return Status.HANDLED;
    }
}
