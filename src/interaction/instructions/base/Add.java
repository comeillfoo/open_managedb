package interaction.instructions.base;

import entity.Mappable;
import interaction.customer.Reciever;

public class Add extends Committer {
  @Override public void Execute() {
    sieve.add(committed);
  }
  public Add(Reciever reciever) {super(reciever);}
  public boolean commit(Mappable element) {
    committed = element;
    return true;
  }
  @Override public String toString() {
    return NAME + " : " + SYNTAX;
  }
  public static final String NAME = "add";
  public static final String BRIEF = "добавляет новый элемент в коллекцию";
  public static final String SYNTAX = NAME + " {element}";
  public static final String DESCRIPTION = "Аргумент в фигурных скобках указывается после\n\t" +
      "ввода команды отдельно по приглашении ко вводу\n\t" +
      "всех требуемых полей.";
}