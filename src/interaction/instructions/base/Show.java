package interaction.instructions.base;

import interaction.customer.Receiver;

import java.io.PrintStream;

/**
 * Класс команды выводящий элементы в стандартный поток вывода
 */
public class Show extends Recorder {
  public Show(Receiver receiver, PrintStream printer) {
    super(receiver, printer);
  }
  @Override public void execute() {
    String list = sieve.survey((subject)->(true));
    if (list.isEmpty()) printer.println("Коллекция пуста");
    else printer.print(list);
  }
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "show";
  public static final String BRIEF = "выводит все элементы в stdout";
  public static final String SYNTAX = NAME;
}