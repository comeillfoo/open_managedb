package interaction.instructions.base;

import interaction.customer.Receiver;
import interaction.instructions.Decree;

/**
 * Класс команды очистки коллекции
 */
public class Clear extends Decree {
  public Clear(Receiver receiver) {super(receiver);}
  @Override public void execute() {sieve.clear();}
  @Override public String toString() {
    return NAME + " : " + SYNTAX;
  }
  public static final String NAME = "clear";
  public static final String BRIEF = "очищает коллекцию";
  public static final String SYNTAX = NAME;
  public static final String DESCRIPTION = "Удаляет из коллекции все элементы. Равнозначно вызову метода clear().";
}