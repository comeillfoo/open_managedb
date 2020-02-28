package interaction.instructions.base;

import interaction.customer.Receiver;

import java.io.PrintStream;

public class Show extends Recorder {
  public Show(Receiver receiver, PrintStream printer) {
    super(receiver, printer);
  }
  @Override public void execute() { printer.println(sieve.survey((subject)->(true))); }
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "show";
  public static final String BRIEF = "выводит все элементы в stdout";
  public static final String SYNTAX = NAME;
  public static final String DESCRIPTION = "Выводит в стандартный поток вывода все элементы\n\t" +
      "коллекции в их строковом представлении. Для\n\t" +
      "всех элементов коллекции вызывается метод toString().";
}