package daggertutorial;

import java.util.List;

import javax.inject.Inject;

import daggertutorial.Database.Account;

final class LogoutCommand implements Command {

  private final Outputter outputter;
  private final Account account;

  @Inject
  LogoutCommand(Outputter outputter, Account account) {
    this.outputter = outputter;
    this.account = account;
  }

  @Override
  public Result handleInput(List<String> input) {
    if (!input.isEmpty()) {
      return Result.invalid();
    }
    outputter.output("logged out " + account.username());
    return Result.inputCompleted();
  }
}
