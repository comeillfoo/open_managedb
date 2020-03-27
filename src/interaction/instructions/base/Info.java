package interaction.instructions.base;

import interaction.customer.Receiver;
import java.io.PrintStream;

/**
 * Класс команды по выводу информации по коллекции
 */
public class Info extends Recorder {
  public Info(Receiver receiver, PrintStream printer) {super(receiver, printer);}
  @Override public void execute() { printer.println(sieve.review()); }
  @Override public String toString() {
    return NAME + " : " + SYNTAX;
  }
  public static final String NAME = "info";
  public static final String BRIEF = "выводит информацию о коллекции";
  public static final String SYNTAX = NAME;
}