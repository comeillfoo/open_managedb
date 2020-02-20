package interaction.instructions;

public class ExecuteScript implements Command {
  @Override public void Execute(Reciever reciever) {}
  public String ToString() {
    return "execute_script";
  }
  private final static String NAME = this.ToString();
  private final static String BRIEF = "исполняет скрипт по указанному имени файла";
  private final static String SYNTAX = this.ToString() + " [file_name]";
  private final static String DESCRIPTION = "Считывает из указанного файла и исполняет\n\t" +
      "скрипт, содержащий те же команды, что и\n\t" +
      "предлагаются ввести пользователю в интерактивном режиме.";
}