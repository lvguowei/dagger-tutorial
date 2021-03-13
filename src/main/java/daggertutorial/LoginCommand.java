package daggertutorial;

import javax.inject.Inject;

import daggertutorial.Database.Account;

final class LoginCommand extends SingleArgCommand {

    private final Outputter outputter;
    private final Database database;

    @Inject
    LoginCommand(Database database, Outputter outputter) {
        this.outputter = outputter;
        this.database = database;
    }

    @Override
    protected Result handleArg(String username) {
        Account account = database.getAccount(username);
        outputter.output(username + " is logged in with balance: " + account.balance());
        return Result.handled();
    }
}
