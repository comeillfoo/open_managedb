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
  public static final String BRIEF = "заменяет на новое значение по ключу [key], если оно больше старого";
  public static final String SYNTAX = NAME + " [key] {element}";
  public static final String DESCRIPTION = "Аргумент в квадратных скобках указывается в той же строке, что\n\t" +
      "и название команды, в то время как все параметры экземпляра указываются по очереди\n\t" +
      "в интерактивном режиме. По окончании работы команда заменит, либо не заменит новым значением элемент, находящийся по ключу [key]";
}
