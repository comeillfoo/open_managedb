package interaction.instructions.extended;

import interaction.instructions.Command;

public class SumOfAnnual implements Command {
  @Override public void Execute() {}
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "sum_of_annual";
  public static final String BRIEF = "";
  public static final String SYNTAX = NAME + "_turnover";
  public static final String DESCRIPTION = "";
}