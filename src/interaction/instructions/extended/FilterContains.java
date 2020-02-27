package interaction.instructions.extended;

import interaction.customer.Reciever;
import interaction.instructions.Decree;

public class FilterContains extends Decree {
  public FilterContains(Reciever reciever) {
    super(reciever);
  }

  @Override public void Execute() {sieve.filterContaines();}
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "filter_contains";
  public static final String BRIEF = "Поиск по заданной строке \"name\".";
  public static final String SYNTAX = NAME + "_name [name]";
  public static final String DESCRIPTION = "Выполняется поиск по строке,которую задает пользователь в фигурных скобках.";
}