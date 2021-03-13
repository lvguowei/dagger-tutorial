package daggertutorial;

import java.util.List;

import javax.inject.Inject;

class HelloWorldCommand implements Command {

    private final Outputter outputter;

    @Inject
    HelloWorldCommand(Outputter outputter) {
        this.outputter = outputter;
    }

    @Override
    public Result handleInput(List<String> input) {
        outputter.output("world!");
        if (!input.isEmpty()) {
            return Result.invalid();
        }
        System.out.println("world!");
        return Result.handled();
    }
}
