package interaction.instructions;

import interaction.customer.Receiver;

public abstract class Decree implements Command {
  protected final Receiver sieve;
  @Override public String toString() {
    return NAME + " : " + SYNTAX;
  }
  protected Decree(Receiver receiver) {sieve = receiver;}
  protected static final String NAME = null;
  protected static final String BRIEF = null;
  protected static final String SYNTAX = null;
  protected static final String DESCRIPTION = null;
}
