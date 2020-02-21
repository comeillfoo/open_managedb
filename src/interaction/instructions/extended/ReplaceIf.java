package interaction.instructions.extended;

import interaction.instructions.Command;

public class ReplaceIf implements Command {
  @Override public void Execute() {}
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "replace_if";
  public static final String BRIEF = "";
  public static final String SYNTAX = NAME;
  public static final String DESCRIPTION = "";
}