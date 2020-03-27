package interaction.instructions.extended;

import interaction.customer.Receiver;

import java.io.PrintStream;

/**
 * Класс команды подсчета суммы полей annualTurnover элементов коллекции
 */
public class SumOfAnnualTurnover extends SumOfAnnual {
  public SumOfAnnualTurnover(Receiver receiver, PrintStream printer) {
    super(receiver, printer);
  }
  @Override
  public void execute() {
    String list = sieve.survey((subject)->(true));
    String[] lines = list.split("\n");
    float sum = 0;
    try {
      for (String line : lines) {
        String annualTurnover = line.split(";")[6].trim().split(" ")[1];
        sum += Float.valueOf(annualTurnover);
      }
    }catch (ArrayIndexOutOfBoundsException e){
      e.getMessage();
    }

    printer.println(sum);
  }
  @Override public String toString() { return NAME + " : " + SYNTAX; }
  public static final String NAME = "sum_of_annual_turnover";
  public static final String BRIEF = "выводит сумму поля \"turnover\" всех элементов коллекции";
  public static final String SYNTAX = NAME;
  public static final String DESCRIPTION = "В поток вывода печатается сумма полей annualTurnover элементов коллекции.";
}
