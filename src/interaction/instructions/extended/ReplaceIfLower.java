package interaction.instructions.extended;

import entity.Organization;
import interaction.customer.Receiver;
import interaction.instructions.extended.comparators.OrganizationComparator;
import interaction.sender.Prompter;

public class ReplaceIfLower extends ReplaceIf {
  private Integer key;
  public ReplaceIfLower(Receiver receiver) {
    super(receiver);
    litmus = (subject) -> (new OrganizationComparator().compare((Organization) sieve.cook(committed), (Organization) subject) == -1);
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
    } else return false;
  }

  @Override
  public void execute() { sieve.add(key, sieve.cook(committed), litmus); }
  public static final String NAME = "replace_if_lower";
  public static final String BRIEF = "заменяет на новое значение по ключу [key], если оно меньше старого";
  public static final String SYNTAX = NAME + " [key] {element}";
  public static final String DESCRIPTION = "Аргумент в квадратных скобках указывается в той же строке, что\n\t" +
      "и название команды, в то время как все параметры экземпляра {element} указываются по очереди\n\t" +
      "в интерактивном режиме с новой строки.";
}
