package daggertutorial;

import java.math.BigDecimal;

import javax.inject.Inject;

import daggertutorial.Database.Account;

final class WithdrawCommand extends BigDecimalCommand {

    private final Account account;
    private final Outputter outputter;
    private final BigDecimal minimumBalance;
    private final BigDecimal maximumWithdraw;
    private final WithdrawalLimiter withdrawalLimiter;

    @Inject
    WithdrawCommand(Outputter outputter, Account account, @MinimumBalance BigDecimal mininumBalance,
            @MaximumWithdraw BigDecimal maximumWithdraw, WithdrawalLimiter withdrawalLimiter) {
        super(outputter);
        this.outputter = outputter;
        this.account = account;
        this.minimumBalance = mininumBalance;
        this.maximumWithdraw = maximumWithdraw;
        this.withdrawalLimiter = withdrawalLimiter;
    }

    @Override
    protected void handleAmount(BigDecimal amount) {
        BigDecimal remainingWithdrawalLimit = withdrawalLimiter.remainingWithdrawalLimit();
        if (amount.compareTo(remainingWithdrawalLimit) > 0) {
            outputter.output(String.format("you may not withdraw %s; you may withdraw %s more in this session", amount,
                    remainingWithdrawalLimit));
            return;
        }

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
            withdrawalLimiter.recordWithdrawal(amount);
        }
    }
}
