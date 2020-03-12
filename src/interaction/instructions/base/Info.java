package interaction.instructions.base;

import interaction.customer.Receiver;
import java.io.PrintStream;

public class Info extends Recorder {
  public Info(Receiver receiver, PrintStream printer) {super(receiver, printer);}
  @Override public void execute() { printer.println(sieve.review()); }
  @Override public String toString() {
    return NAME + " : " + SYNTAX;
  }
  public static final String NAME = "info";
  public static final String BRIEF = "выводит информацию о коллекции";
  public static final String SYNTAX = NAME;
  public static final String DESCRIPTION = "Выводит в стандартный поток вывода информацию о загруженной коллекции:\n\t" +
      "* тип ее элементов;\n\t" +
      "* дату инициализации;\n\t" +
      "* количество элементов;";
}