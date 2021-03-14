package daggertutorial;

import java.math.BigDecimal;

import javax.inject.Inject;

import daggertutorial.Database.Account;

final class WithdrawCommand extends BigDecimalCommand {

    private final Account account;
    private final Outputter outputter;
    private final BigDecimal minimumBalance;
    private final BigDecimal maximumWithdraw;

    @Inject
    WithdrawCommand(Outputter outputter, Account account, @MinimumBalance BigDecimal mininumBalance,
            @MaximumWithdraw BigDecimal maximumWithdraw) {
        super(outputter);
        this.outputter = outputter;
        this.account = account;
        this.minimumBalance = mininumBalance;
        this.maximumWithdraw = maximumWithdraw;
    }

    @Override
    protected void handleAmount(BigDecimal amount) {
        if (amount.compareTo(maximumWithdraw) > 0) {
            outputter.output("Exceeds withdraw limit");
            return;
        }

        BigDecimal newBalance = account.balance().subtract(amount);

        if (newBalance.compareTo(minimumBalance) < 0) {
            outputter.output("Not enough balance");
            return;
        } else {
            account.withdraw(amount);
            outputter.output("Your balance is: " + account.balance());
        }
    }
}
