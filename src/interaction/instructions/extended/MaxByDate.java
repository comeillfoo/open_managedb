package interaction.instructions.extended;

import interaction.customer.Receiver;

public class MaxByDate extends MaxBy  {
  public MaxByDate(Receiver receiver) {
    super(receiver);
  }

  @Override
  public void execute() {
    String list = sieve.survey((subject)->(true));
    String[] lines = list.split("\n");
    for (String line : lines) {
    }
  }
  public static final String NAME = "max_by_creation_date";
  public static final String BRIEF = "находит самый старейший элемент коллекции";
  public static final String SYNTAX = NAME;
  public static final String DESCRIPTION = "Среди всех значений коллекции выводит только тот,\n\t" +
      "который имеет максимальное значение поля creationDate";
}
