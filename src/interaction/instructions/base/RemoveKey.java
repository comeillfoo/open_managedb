package interaction.instructions.base;

import interaction.customer.Reciever;
import interaction.instructions.Decree;

public class RemoveKey extends Decree {

  public RemoveKey(Reciever sieve) {
    super(sieve);
  }

  @Override
  public void Execute() {
    sieve.removeKey();
  }

  @Override
  public String toString() {
    return NAME + " : " + SYNTAX;
  }

  public static final String NAME = "remove_key";
  public static final String BRIEF = "";
  public static final String SYNTAX = NAME + " [key]";
  public static final String DESCRIPTION = "";
}