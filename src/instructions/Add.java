package instructions;

public class Add implements Command {
  @Override public void Execute() {}
  public String ToString() {
    return "add";
  }
  private final String NAME = this.ToString();
  private final String BRIEF = "добавляет новый элемент в коллекцию";
  private final String SYNTAX = this.ToString() + " {element}";
  private final String DESCRIPTION = "Аргумент в фигурных скобках указывается после\n\t" +
      "ввода команды отдельно по приглашении ко вводу\n\t" +
      "всех требуемых полей.";
}