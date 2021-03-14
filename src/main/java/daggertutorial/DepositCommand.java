package daggertutorial;

import java.math.BigDecimal;

import javax.inject.Inject;

import daggertutorial.Database.Account;

final class DepositCommand extends BigDecimalCommand {

    private final Account account;
    private final Outputter outputter;
    private final WithdrawalLimiter withdrawalLimiter;

    @Inject
    DepositCommand(Account account, Outputter outputter, WithdrawalLimiter withdrawalLimiter) {
        super(outputter);
        this.account = account;
        this.outputter = outputter;
        this.withdrawalLimiter = withdrawalLimiter;
    }

	@Override
	protected void handleAmount(BigDecimal amount) {
      account.deposit(amount);
      withdrawalLimiter.recordDeposit(amount);
      outputter.output(account.username() + " now has: " + account.balance());
	}
}
