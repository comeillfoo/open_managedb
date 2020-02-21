package interaction.instructions.base;

import interaction.instructions.Command;

public class Save implements Command {
  @Override public void Execute() {}
  @Override public String toString() {return "save";}
  public static final NAME = toString();
  public static final BRIEF = "";
  public static final SYNTAX = toString();
  public static final DESCRIPTION = "";
}