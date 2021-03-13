package dagger.tutorial;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import dagger.tutorial.Database.Account;

final class DepositCommand implements Command {

    private final Database database;
    private final Outputter outputter;

    @Inject
    DepositCommand(Database database, Outputter outputter) {
        this.database = database;
        this.outputter = outputter;
    }
    
    @Override
    public Status handleInput(List<String> input) {
        if (input.size() != 2) {
            return Status.INVALID;
        }
        Account account = database.getAccount(input.get(0));
        account.deposit(new BigDecimal(input.get(1)));
        outputter.output(account.username() + " now has " + account.balance());
        return Status.HANDLED;
    }
    
}
