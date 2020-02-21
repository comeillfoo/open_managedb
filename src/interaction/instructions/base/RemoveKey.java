package interaction.instructions.base;

import interaction.instructions.Command;

public class RemoveKey implements Command {
  @Override public void Execute() {}
  @Override public String toString() {return "remove_key";}
  public static final NAME = toString();
  public static final BRIEF = "";
  public static final SYNTAX = toString() + " [key]";
  public static final DESCRIPTION = "";
}