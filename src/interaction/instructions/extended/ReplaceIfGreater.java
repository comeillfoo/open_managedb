package interaction.instructions.extended;

import entity.Organization;
import interaction.customer.Receiver;
import interaction.instructions.extended.comparators.OrganizationComparator;
import interaction.sender.Prompter;

/**
 * Команда "replace_if_greater" заменяет объект на пользовательский,если параметры этого объекта меньще чем у пользовательского объекта.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public class ReplaceIfGreater extends ReplaceIf {
  private Integer key;

  public ReplaceIfGreater(Receiver receiver) {
    super(receiver);
    litmus = (subject) -> (new OrganizationComparator().compare((Organization) sieve.cook(committed), (Organization) subject) > 0);
  }

  /**
   * Метод сохраняющий ссылку на значение ключа если объект с заданным ключом существует.
   * @param key ключ по которому будет заменяться объект
   * @return boolean
   */
  public boolean openKey(Integer key) {
    if (key != null && sieve.lookFor(key).getName().equals("Sample Organization") == false) {
      this.key = key;
      return true;
    }
    else {
      key = -1;
      return false;
    }
  }

  /**
   * @param element сам добавляемый элемент, точнее его параметры
   * @return
   */
  @Override
  public boolean commit(Prompter.Junker element) {
    if (element != null) {
      committed = element;
      return true;
    }
    return false;
  }
  @Override
  public void execute() {
    sieve.add(key, sieve.cook(committed), litmus);
    if(litmus.detect(sieve.lookFor(key))) {
      System.out.println("Replacement:success!");
    }else System.out.println("Replacement:not produced!");
  }
  @Override
  public String toString(){ return NAME + " : " + SYNTAX; }

  public static final String NAME = "replace_if_greater";
  public static final String BRIEF = "заменяет на новое значение по ключу [key], если оно больше старого";
  public static final String SYNTAX = NAME + " [key] {element}";
  public static final String DESCRIPTION = "Аргумент в квадратных скобках указывается в той же строке, что\n\t" +
      "и название команды, в то время как все параметры экземпляра указываются по очереди\n\t" +
      "в интерактивном режиме. По окончании работы команда заменит, либо не заменит новым значением элемент, находящийся по ключу [key]";
}
