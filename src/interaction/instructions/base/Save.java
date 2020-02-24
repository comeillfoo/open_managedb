package interaction.instructions.base;

import interaction.customer.Reciever;
import interaction.instructions.Decree;

public class Save extends Decree {

  public Save(Reciever reciever) {
    super(reciever);
  }

  @Override public void Execute() {
    sieve.save();
  }
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "save";
  public static final String BRIEF = "Сохраняет коллекцию в файл.";
  public static final String SYNTAX = NAME + " {\"The name of the file\"}";
  public static final String DESCRIPTION = "В фигурных скобках указываеться название файла,\n" +
          " под которым будет сохранена коллекция.";
}