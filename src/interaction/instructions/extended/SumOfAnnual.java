package interaction.instructions.extended;

import interaction.customer.Reciever;
import interaction.instructions.Decree;

public class SumOfAnnual extends Decree {
  protected SumOfAnnual(Reciever reciever) {
    super(reciever);
  }

  @Override public void execute() {}
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "sum_of_annual";
  public static final String BRIEF = "";
  public static final String SYNTAX = NAME + "_turnover";
  public static final String DESCRIPTION = "";
}