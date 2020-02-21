package interaction.instructions.base;

import interaction.instructions.Command;

public class Insert implements Command {
  @Override public void Execute() {}
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "insert";
  public static final String BRIEF = "";
  public static final String SYNTAX = NAME + " [key] {element}";
  public static final String DESCRIPTION = "";
}
