package interaction.instructions;

import interaction.customer.Reciever;

public abstract class Decree implements Command {
  protected final Reciever sieve;
  @Override public String toString() {
    return NAME + " : " + SYNTAX;
  }
  protected Decree(Reciever reciever) {sieve = reciever;}
  protected static final String NAME = null;
  protected static final String BRIEF = null;
  protected static final String SYNTAX = null;
  protected static final String DESCRIPTION = null;
}
