package daggertutorial;

import java.util.Optional;

import javax.inject.Inject;

import daggertutorial.Database.Account;


final class LoginCommand extends SingleArgCommand {

    private final Outputter outputter;
    private final Database database;
    private final UserCommandsRouter.Factory userCommandsRouterFactory;
    private final Optional<Account> account;

    @Inject
    LoginCommand(Database database, Outputter outputter, UserCommandsRouter.Factory userCommandRouterFactory, Optional<Account> account) {
        this.outputter = outputter;
        this.database = database;
        this.userCommandsRouterFactory = userCommandRouterFactory;
        this.account = account;
    }

    @Override
    protected Result handleArg(String username) {
        if (account.isPresent()) {
            return Result.handled();
        }
        
        Account account = database.getAccount(username);
        outputter.output(username + " is logged in with balance: " + account.balance());
        return Result.enterNestedCommandSet(userCommandsRouterFactory.create(account).router());
    }
}

