package interaction.instructions.base;

import interaction.customer.Reciever;
import interaction.instructions.Decree;

public class Show extends Decree {

  public Show(Reciever reciever) {
    super(reciever);
  }

  @Override
  public void Execute() {
    sieve.show();
  }

  @Override
  public String toString() {
    return NAME + " : " + SYNTAX;
  }

  public static final String NAME = "show";
  public static final String BRIEF = "выводит все элементы в stdout";
  public static final String SYNTAX = NAME;
  public static final String DESCRIPTION = "Выводит в стандартный поток вывода все элементы\n\t" +
          "коллекции в их строковом представлении. Для\n\t" +
          "всех элементов коллекции вызывается метод toString().";
}