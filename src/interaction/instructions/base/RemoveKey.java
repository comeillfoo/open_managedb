package interaction.instructions.base;

import interaction.instructions.Command;

public class RemoveKey implements Command {
  @Override public void Execute() {}
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "remove_key";
  public static final String BRIEF = "";
  public static final String SYNTAX = NAME + " [key]";
  public static final String DESCRIPTION = "";
}