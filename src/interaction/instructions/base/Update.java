package interaction.instructions.base;

import interaction.instructions.Command;

public class Update implements Command {
  @Override public void Execute() {}
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "update";
  public static final String BRIEF = "";
  public static final String SYNTAX = NAME + " id {element}";
  public static final String DESCRIPTION = "";
}