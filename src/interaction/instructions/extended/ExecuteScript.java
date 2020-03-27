package interaction.instructions.extended;

import interaction.customer.Receiver;
import interaction.instructions.Command;
import interaction.instructions.Decree;
import interaction.sender.FilePrompter;

import java.io.*;
import java.util.Map;

/**
 * Класс команды обработки скриптов.
 * Все скрипты обязаны лежать в директории scripts корня проекта
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public class ExecuteScript extends Decree {
  private String name;
  private Map<String, Command> list;
  public ExecuteScript(Receiver receiver) {
    super(receiver);
  }
  @Override public void execute() {
    String userdir = System.getProperty("user.dir");
    String sep = System.getProperty("file.separator");
    try (InputStream istream = new FileInputStream(userdir + sep + "scripts" + sep + name)) {
      FilePrompter stepper = new FilePrompter(System.out, istream);
      for (Map.Entry<String, Command> e : list.entrySet()) { stepper.signup(e.getKey(), e.getValue()); }
      stepper.scan();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public void setFile(String name) { this.name = name == null || name.isEmpty()? "test1.pt" : name; }
  @Override public String toString() {
    return NAME + " : " + SYNTAX;
  }
  public void setCommandList(Map<String, Command> list) { this.list = list; }
  public static final String NAME = "execute_script";
  public static final String BRIEF = "исполняет скрипт по указанному имени файла";
  public static final String SYNTAX = NAME + " [file_name]";
}