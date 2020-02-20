package interaction.instructions;

public class Show implements Command {
  @Override public void Execute() {}
  public String ToString() {
    return "show";
  }
  private final static String NAME = this.ToString();
  private final static String BRIEF = "выводит все элементы в stdout";
  private final static String SYNTAX = this.ToString();
  private final static String DESCRIPTION = "Выводит в стандартный поток вывода все элементы\n\t" +
      "коллекции в их строковом представлении. Для\n\t" +
      "всех элементов коллекции вызывается метод toString().";
}