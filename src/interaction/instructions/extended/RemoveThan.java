package interaction.instructions.extended;

import interaction.instructions.Command;

public class RemoveThan implements Command {
  @Override public void Execute() {}
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "remove";
  public static final String BRIEF = "";
  public static final String SYNTAX = NAME + "_lower {element}";
  public static final String DESCRIPTION = "";
}