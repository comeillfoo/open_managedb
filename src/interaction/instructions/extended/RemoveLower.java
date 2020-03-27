package interaction.instructions.extended;

import entity.Organization;
import interaction.customer.Receiver;
import interaction.instructions.extended.comparators.OrganizationComparator;
import interaction.sender.Prompter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Команда "remove_lower" удаляет объект имеющий параметры ниже заданных пользователем.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public class RemoveLower extends RemoveThan {
  public RemoveLower(Receiver receiver) {
    super(receiver);
    litmus = (subject) -> (new OrganizationComparator().compare((Organization) sieve.cook(committed), (Organization) subject) < 0);
  }

  @Override
  public void execute() {
    String list = sieve.survey(litmus);
    String[] lines = list.split("\n");
    Pattern date = Pattern.compile("key: \\d+;");
    Matcher matcher;
    Integer key;
    int count = 0;
    for (String line : lines) {
      matcher = date.matcher(line);
      if (matcher.find()) {
        key = Integer.valueOf(line.substring(matcher.start() + 5, matcher.end() - 1));
        sieve.remove(key);
        count++;
      }
    }
    System.out.println("Objects removed from the collection: "+count);
  }

  public boolean commit(Prompter.Junker element){
    commit(element);
    return false;
  }

  @Override public String toString() { return NAME + " : " + SYNTAX; }
  public static final String NAME = "remove_lower";
  public static final String BRIEF = "удаляет из коллекции элементы, меньшие чем заданный";
  public static final String SYNTAX = NAME + " {element}";
}
