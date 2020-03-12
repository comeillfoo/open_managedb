package interaction.instructions.base;

import interaction.customer.Receiver;
import interaction.instructions.Decree;

public class Save extends Decree {
  public Save(Receiver receiver) {super(receiver);}
  @Override public void execute() { sieve.unload(); }
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "save";
  public static final String BRIEF = "Сохраняет коллекцию в файл.";
  public static final String SYNTAX = NAME;
  public static final String DESCRIPTION = "Сохраняет коллекцию в файл, с назваанием указанным в переменной окружения,\n" +
      "использовавшейся при старте программы.";
}