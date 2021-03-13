package dagger.tutorial;

import java.util.List;

interface Command {
  Status handleInput(List<String> input);
  enum Status {
    INVALID,
    HANDLED
  }
}
