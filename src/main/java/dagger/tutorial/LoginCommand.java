package dagger.tutorial;

import javax.inject.Inject;

import dagger.tutorial.Database.Account;

final class LoginCommand extends SingleArgCommand {

    private final Outputter outputter;
    private final Database database;

    @Inject
    LoginCommand(Database database, Outputter outputter) {
        this.outputter = outputter;
        this.database = database;
    }

    @Override
    protected Status handleArg(String username) {
        Account account = database.getAccount(username);
        outputter.output(username + " is logged in with balance: " + account.balance());
        return Status.HANDLED;
    }
}
