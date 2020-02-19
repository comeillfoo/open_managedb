package instructions;

public class Clear implements Command {
  @Override public void Execute() {}
  public String ToString() {
    return "clear";
  }
  private final String NAME = this.ToString();
  private final String BRIEF = "очищает коллекцию";
  private final String SYNTAX = this.ToString();
  private final String DESCRIPTION = "Удаляет из коллекции все элементы. Равнозначно вызову метода clear().";
}