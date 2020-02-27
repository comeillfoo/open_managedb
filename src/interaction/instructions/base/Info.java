package interaction.instructions.base;

import interaction.customer.Reciever;
import java.io.PrintStream;

public class Info extends Recorder {
  public Info(Reciever reciever, PrintStream printer) {super(reciever, printer);}
  @Override public void Execute() { printer.println(sieve.review()); }
  @Override public String toString() {
    return NAME + " : " + SYNTAX;
  }
  public static final String NAME = "info";
  public static final String BRIEF = "выводит информацию о коллекции";
  public static final String SYNTAX = NAME;
  public static final String DESCRIPTION = "Выводит в стандартный поток вывода информацию о загруженной коллекции:\n\t" +
      "] тип ее элементов;\n\t" +
      "] дату инициализации;\n\t" +
      "] количество элементов;";
}