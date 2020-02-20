package interaction.instructions;

public class Clear implements Command {

  @Override public void Execute() {}
  public String ToString() {
    return "clear";
  }
  public final static String NAME = this.ToString();
  public final static String BRIEF = "очищает коллекцию";
  public final static String SYNTAX = this.ToString();
  public final static String DESCRIPTION = "Удаляет из коллекции все элементы. Равнозначно вызову метода clear().";
}