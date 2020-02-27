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
  public static final String NAME = "remove_lower";
}
