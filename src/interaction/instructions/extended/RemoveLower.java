package interaction.instructions.extended;

import entity.Organization;
import interaction.customer.Reciever;
import interaction.instructions.extended.comparators.OrganizationComparator;

public class RemoveLower extends RemoveThan {
  public RemoveLower(Reciever reciever) {
    super(reciever);
    litmus = (subject) -> (new OrganizationComparator().compare((Organization) sieve.cook(committed), (Organization) subject) == -1);
  }
  @Override
  public void execute() {
    String list = new String(sieve.survey(litmus));
    String[] lines = list.split("\n");
    for (String line : lines) {
      Integer key = Integer.parseInt(line.split("key: (.*)? (.*)?")[0]);
      sieve.remove(key);
    }
  }
  @Override public String toString() { return NAME + " : " + SYNTAX; }
  public static final String NAME = "remove_lower";
  public static final String BRIEF = "удаляет из коллекции элементы, меньшие чем заданный";
  public static final String SYNTAX = NAME + " {element}";
  public static final String DESCRIPTION = "Аргумент в фигурных скобках -- это экземпляр класса,\n\t" +
      "хранящегося в коллекции, данные которого\n\t" +
      "предлагаются ввести пользователю в интерактивном режиме по очереди.";
}
