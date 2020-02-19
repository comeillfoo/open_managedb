package instructions;

public class Info implements Command {
  @Override public void Execute() {}
  public String ToString() {
    return "info";
  }
  private final String NAME = this.ToString();
  private final String BRIEF = "выводит информацию о коллекции";
  private final String SYNTAX = this.ToString();
  private final String DESCRIPTION = "Выводит в стандартный поток вывода информацию о загруженной коллекции:\n\t" +
      "] тип ее элементов;\n\t" +
      "] дату инициализации;\n\t" +
      "] количество элементов;";
}