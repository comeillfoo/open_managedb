package interaction.instructions.base;

import interaction.customer.Reciever;
import interaction.instructions.Decree;

public class Exit extends Decree {
  public Exit(Reciever reciever) {super(reciever);}
  @Override public void Execute() {System.exit(0);} // TODO: check if it is bad decision;
  public String ToString() {
    return NAME + " : " + SYNTAX;
  }
  public static final String NAME = "exit";
  public static final String BRIEF = "завершить программу";
  public static final String SYNTAX = NAME;
  public static final String DESCRIPTION = "Выход из интерактивного режима без записи данных в файл.\n\t" +
      "!!! ACHTUNG !!! Все внесённые изменения будут\n\t" +
      "утеряны, желательно перед выходом исполнить команду save.";
}