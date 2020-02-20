package interaction.instructions;

public class Exit implements Command {
  @Override public void Execute(Reciever reciever) {}
  public String ToString() {
    return "exit";
  }
  private final static String NAME = this.ToString();
  private final static String BRIEF = "завершить программу";
  private final static String SYNTAX = this.ToString();
  private final static String DESCRIPTION = "Выход из интерактивного режима без записи данных в файл.\n\t" +
      "!!! ACHTUNG !!! Все внесённые изменения будут\n\t" +
      "утеряны, желательно перед выходом исполнить команду save.";
}