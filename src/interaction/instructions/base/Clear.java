package interaction.instructions.base;

import interaction.customer.Reciever;
import interaction.instructions.Decree;

public class Clear extends Decree {
  public Clear(Reciever reciever) {super(reciever);}
  @Override public void Execute() {sieve.clear();}
  @Override public String toString() {
    return NAME + " : " + SYNTAX;
  }
  public static final String NAME = "clear";
  public static final String BRIEF = "очищает коллекцию";
  public static final String SYNTAX = NAME;
  public static final String DESCRIPTION = "Удаляет из коллекции все элементы. Равнозначно вызову метода clear().";
}