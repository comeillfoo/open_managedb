package interaction.instructions.extended;

import interaction.instructions.Command;

public class MaxBy implements Command {
  @Override public void Execute() {}
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "max_by";
  public static final String BRIEF = "";
  public static final String SYNTAX = NAME + "_creation_date";
  public static final String DESCRIPTION = "";
}