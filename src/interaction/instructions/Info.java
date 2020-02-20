package interaction.instructions;

public class Info implements Command {
  @Override public void Execute(Reciever reciever) {}
  public String ToString() {
    return "info";
  }
  private final static String NAME = this.ToString();
  private final static String BRIEF = "выводит информацию о коллекции";
  private final static String SYNTAX = this.ToString();
  private final static String DESCRIPTION = "Выводит в стандартный поток вывода информацию о загруженной коллекции:\n\t" +
      "] тип ее элементов;\n\t" +
      "] дату инициализации;\n\t" +
      "] количество элементов;";
}