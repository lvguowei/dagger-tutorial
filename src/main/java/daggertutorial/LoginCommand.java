package daggertutorial;

import javax.inject.Inject;

import daggertutorial.Database.Account;


final class LoginCommand extends SingleArgCommand {

    private final Outputter outputter;
    private final Database database;
    private final UserCommandsRouter.Factory userCommandsRouterFactory;

    @Inject
    LoginCommand(Database database, Outputter outputter, UserCommandsRouter.Factory userCommandRouterFactory) {
        this.outputter = outputter;
        this.database = database;
        this.userCommandsRouterFactory = userCommandRouterFactory;
    }

    @Override
    protected Result handleArg(String username) {
        Account account = database.getAccount(username);
        outputter.output(username + " is logged in with balance: " + account.balance());
        return Result.enterNestedCommandSet(userCommandsRouterFactory.create(account).router());
    }
}

