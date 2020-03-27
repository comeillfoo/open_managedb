package interaction.instructions.extended;

import interaction.customer.Receiver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Команда "max_by_date" позволяет найти объект,созданный позже всех.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public class MaxByDate extends MaxBy  {
  public MaxByDate(Receiver receiver) {
    super(receiver);
  }

  /**
   * Метод execute, содержащий в себе логику нахождения объекта с поздней датой.
   */
  @Override
  public void execute() {
    String list = sieve.survey((subject)->(true));
    String[] lines = list.split("\n");
    Pattern date = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    Matcher matcher;
    String hasMaxDate = "";
    String[] maxDate = new String[3];
    String[] temp2 ;
    for (String line : lines) {
      matcher = date.matcher(line);
      matcher.find();
      if (matcher.find() == false) {
        System.out.println("Collection is empty");
        return;
      }
      if(hasMaxDate.isEmpty()){
        hasMaxDate = line;
        maxDate = line.substring(matcher.start(),matcher.end()).split("-");
        continue;
      }

      temp2 = line.substring(matcher.start(),matcher.end()).split("-");
      if(Integer.valueOf(maxDate[0]) < Integer.valueOf(temp2[0])){
        hasMaxDate = line;
        maxDate = temp2;
      }else if(Integer.valueOf(maxDate[1]) < Integer.valueOf(temp2[1])){
        hasMaxDate = line;
        maxDate = temp2;
      }else if(Integer.valueOf(maxDate[2]) < Integer.valueOf(temp2[2])){
        hasMaxDate = line;
        maxDate = temp2;
      }
    }
    System.out.println(hasMaxDate);
  }

  @Override
  public String toString(){ return NAME + " : " + SYNTAX; }

  public static final String NAME = "max_by_creation_date";
  public static final String BRIEF = "находит самый старейший элемент коллекции";
  public static final String SYNTAX = NAME;
  public static final String DESCRIPTION = "Среди всех значений коллекции выводит только тот,\n\t" +
      "который имеет максимальное значение поля creationDate";
}
