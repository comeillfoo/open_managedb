package interaction.instructions.extended;

import entity.Organization;
import interaction.customer.Receiver;
import interaction.instructions.extended.comparators.OrganizationComparator;
import interaction.sender.Prompter;

public class ReplaceIfGreater extends ReplaceIf {
  private Integer key;
  public ReplaceIfGreater(Receiver receiver) {
    super(receiver);
    litmus = (subject) -> (new OrganizationComparator().compare((Organization) sieve.cook(committed), (Organization) subject) == 1);
  }
  public void openKey(Integer key) {
    if (key != null)
      this.key = key;
    else key = -1;
  }
  @Override
  protected boolean commit(Prompter.ParamsCollector element) {
    if (element != null) {
      committed = element;
      return true;
    }
    return false;
  }
  @Override
  public void execute() { sieve.add(key, sieve.cook(committed), litmus); }
  public static final String NAME = "replace_if_greater";
}
