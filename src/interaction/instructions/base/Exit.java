package interaction.instructions.base;

import interaction.customer.Receiver;
import interaction.instructions.Decree;

/**
 * Класс команды выхода из программы
 */
public class Exit extends Decree {
  public Exit(Receiver receiver) {super(receiver);}
  @Override public void execute() {System.exit(0);} // TODO: check if it is bad decision;
  public String toString() {
    return NAME + " : " + SYNTAX;
  }
  public static final String NAME = "exit";
  public static final String BRIEF = "завершить программу";
  public static final String SYNTAX = NAME;
}