package interaction.instructions.extended;

import interaction.customer.Reciever;

import java.io.PrintStream;

public class SumOfAnnualTurnover extends SumOfAnnual {
  public SumOfAnnualTurnover(Reciever reciever, PrintStream printer) {
    super(reciever, printer);
  }
  @Override
  public void execute() {
    String list = sieve.survey((subject)->(true));
    String[] lines = list.split("\n");
    float sum = 0;
    for (String line : lines) {
      String annualTurnover = line.split("annualTurnover: (.*)?;")[0];
      sum += Float.valueOf(annualTurnover);
    }
    printer.println(sum);
  }
  public static final String NAME = "sum_of_annual_turnover";
  public static final String BRIEF = "заменяет на новое значение по ключу [key], если оно больше старого";
  public static final String SYNTAX = NAME + " [key] {element}";
  public static final String DESCRIPTION = "Аргумент в квадратных скобках указывается в той же строке, что\n\t" +
      "и название команды, в то время как все параметры экземпляра указываются по очереди\n\t" +
      "в интерактивном режиме. По окончании работы команда заменит, либо не заменит новым значением элемент, находящийся по ключу [key]";
}
