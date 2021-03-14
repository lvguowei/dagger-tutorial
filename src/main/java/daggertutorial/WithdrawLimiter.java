package daggertutorial;

import java.math.BigDecimal;

import javax.inject.Inject;

@PerSession
final class WithdrawalLimiter {
  private BigDecimal remainingWithdrawalLimit;

  @Inject
  WithdrawalLimiter(@MaximumWithdraw BigDecimal maximumWithdrawal) {
    this.remainingWithdrawalLimit = maximumWithdrawal;
  }

  void recordDeposit(BigDecimal amount) {
    remainingWithdrawalLimit = remainingWithdrawalLimit.add(amount);
  }

  void recordWithdrawal(BigDecimal amount) {
    remainingWithdrawalLimit = remainingWithdrawalLimit.subtract(amount);
  }

  BigDecimal remainingWithdrawalLimit() {
    return remainingWithdrawalLimit;
  }
}
