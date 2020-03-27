package interaction.instructions.extended;

import entity.Organization;
import interaction.customer.Receiver;
import interaction.instructions.extended.comparators.OrganizationComparator;
import interaction.sender.Prompter;

/**
 * Команда "replace_if_lower" заменяет объект на пользовательский,если параметры этого объекта больше чем у пользовательского объекта.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
public class ReplaceIfLower extends ReplaceIf {
  private Integer key;
  public ReplaceIfLower(Receiver receiver) {
    super(receiver);
    litmus = (subject) -> (new OrganizationComparator().compare((Organization) sieve.cook(committed), (Organization) subject) < 0);
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
    } else return false;
  }

  @Override
  public String toString(){ return NAME + " : " + SYNTAX; }

  @Override
  public void execute() {
    sieve.add(key, sieve.cook(committed), litmus);
    if(litmus.detect(sieve.lookFor(key))) {
      System.out.println("Replacement: success!");
    }else System.out.println("Replacement: not produced!");
  }
  public static final String NAME = "replace_if_lower";
  public static final String BRIEF = "заменяет на новое значение по ключу [key], если оно меньше старого";
  public static final String SYNTAX = NAME + " [key] {element}";
  public static final String DESCRIPTION = "Аргумент в квадратных скобках указывается в той же строке, что\n\t" +
      "и название команды, в то время как все параметры экземпляра {element} указываются по очереди\n\t" +
      "в интерактивном режиме с новой строки.";
}
