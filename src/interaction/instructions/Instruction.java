package interaction.instruction;

import interaction.customer.Reciever;

protected class Instruction implements Command {
  private final Reciever sieve;
  public Instruction(Reciever reciever) {this.sieve = reciever;}
  public final static String NAME;
  public final static String BRIEF;
  public final static String SYNTAX;
  public final static String DESCRIPTION;
}