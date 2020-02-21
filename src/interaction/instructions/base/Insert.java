package interaction.instructions.base;

import interaction.instructions.Command;

public class Insert implements Command {
  @Override public void Execute() {}
  @Override public String toString() {return "insert";}
  public static final NAME = toString();
  public static final BRIEF = "";
  public static final SYNTAX = toString() + " [key] {element}";
  public static final DESCRIPTION = "";
}
