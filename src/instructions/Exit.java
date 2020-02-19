package instructions;

public class Exit implements Command {
  @Override public void Execute() {}
  public String ToString() {
    return "exit";
  }
  private final String NAME = this.ToString();
  private final String BRIEF = "завершить программу";
  private final String SYNTAX = this.ToString();
  private final String DESCRIPTION = "Выход из интерактивного режима без записи данных в файл.\n\t" +
      "!!! ACHTUNG !!! Все внесённые изменения будут\n\t" +
      "утеряны, желательно перед выходом исполнить команду save.";
}