package interaction.instructions.extended;

import interaction.instructions.Command;

public class ExecuteScript implements Command {
  @Override public void Execute() {}
  @Override public String toString() {
    return NAME + " : " + SYNTAX;
  }
  public static final String NAME = "execute_script";
  public static final String BRIEF = "исполняет скрипт по указанному имени файла";
  public static final String SYNTAX = NAME + " [file_name]";
  public static final String DESCRIPTION = "Считывает из указанного файла и исполняет\n\t" +
      "скрипт, содержащий те же команды, что и\n\t" +
      "предлагаются ввести пользователю в интерактивном режиме.";
}