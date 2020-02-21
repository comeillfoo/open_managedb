package interaction.instructions.base;

import interaction.instructions.Command;

public class Save implements Command {
  @Override public void Execute() {}
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "save";
  public static final String BRIEF = "";
  public static final String SYNTAX = NAME;
  public static final String DESCRIPTION = "";
}