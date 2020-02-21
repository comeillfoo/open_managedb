package interaction.instructions.extended;

import interaction.instructions.Command;

public class ExecuteScript implements Command {
  @Override public void Execute() {}
  @Override public String toString() {
    return "execute_script";
  }
  private final static String NAME = toString();
  private final static String BRIEF = "исполняет скрипт по указанному имени файла";
  private final static String SYNTAX = toString() + " [file_name]";
  private final static String DESCRIPTION = "Считывает из указанного файла и исполняет\n\t" +
      "скрипт, содержащий те же команды, что и\n\t" +
      "предлагаются ввести пользователю в интерактивном режиме.";
}