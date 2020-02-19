package instructions;

public class Show implements Command {
  @Override public void Execute() {}
  public String ToString() {
    return "show";
  }
  private final String NAME = this.ToString();
  private final String BRIEF = "выводит все элементы в stdout";
  private final String SYNTAX = this.ToString();
  private final String DESCRIPTION = "Выводит в стандартный поток вывода все элементы\n\t" +
      "коллекции в их строковом представлении. Для\n\t" +
      "всех элементов коллекции вызывается метод toString().";
}