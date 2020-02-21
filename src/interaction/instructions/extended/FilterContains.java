package interaction.instructions.extended;

import interaction.instructions.Command;

public class FilterContains implements Command {
  @Override public void Execute() {}
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "filter_contains";
  public static final String BRIEF = "";
  public static final String SYNTAX = NAME + "_name [name]";
  public static final String DESCRIPTION = "";
}