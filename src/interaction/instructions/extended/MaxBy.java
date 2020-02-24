package interaction.instructions.extended;

import interaction.customer.Reciever;
import interaction.instructions.Decree;

public class MaxBy extends Decree {
  public MaxBy(Reciever reciever) {
    super(reciever);
  }

  @Override public void Execute() {sieve.maxByCreationDate();}
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "max_by";
  public static final String BRIEF = "Выводит организацию созданую раньше всех";
  public static final String SYNTAX = NAME + "_creation_date";
  public static final String DESCRIPTION = "Выводит раннее созданную организацию.Если таких 2 или более," +
          "то возвращает первую из них.";
}