package interaction.instructions.base;

import interaction.customer.Reciever;
import interaction.instructions.Decree;

public class Save extends Decree {
  public Save(Reciever reciever) {super(reciever);}
  @Override public void Execute() { sieve.unload(); }
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "save";
  public static final String BRIEF = "";
  public static final String SYNTAX = NAME;
  public static final String DESCRIPTION = "";
}