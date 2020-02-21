package interaction.instructions.base;

import interaction.instructions.Command;

public class Show implements Command {
  @Override public void Execute() {}
  @Override public String toString() {
    return "show";
  }
  public static final String NAME = toString();
  public static final String BRIEF = "выводит все элементы в stdout";
  public static final String SYNTAX = toString();
  public static final String DESCRIPTION = "Выводит в стандартный поток вывода все элементы\n\t" +
      "коллекции в их строковом представлении. Для\n\t" +
      "всех элементов коллекции вызывается метод toString().";
}