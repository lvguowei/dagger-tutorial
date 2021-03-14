package daggertutorial;

import java.math.BigDecimal;

import javax.inject.Inject;

import daggertutorial.Database.Account;

final class WithdrawCommand extends BigDecimalCommand {

    private final Account account;
    private final Outputter outputter;
    
    @Inject
    WithdrawCommand(Outputter outputter, Account account) {
        super(outputter);
        this.outputter = outputter;
        this.account = account;
    }

    @Override
    protected void handleAmount(BigDecimal amount) {
        BigDecimal newBalance = account.balance().subtract(amount);
        if (newBalance.signum() < 0) {
            outputter.output("Not enough balance");
            return;
        } else {
            account.withdraw(amount);
            outputter.output("Your balance is: " + account.balance());
        }
    }

}
