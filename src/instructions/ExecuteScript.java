package instructions;

public class ExecuteScript implements Command {
  @Override public void Execute() {}
  public String ToString() {
    return "execute_script";
  }
  private final String NAME = this.ToString();
  private final String BRIEF = "исполняет скрипт по указанному имени файла";
  private final String SYNTAX = this.ToString() + " [file_name]";
  private final String DESCRIPTION = "Считывает из указанного файла и исполняет\n\t" +
      "скрипт, содержащий те же команды, что и\n\t" +
      "предлагаются ввести пользователю в интерактивном режиме.";
}