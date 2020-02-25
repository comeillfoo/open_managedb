package interaction.instructions.base;

import interaction.customer.Reciever;
import interaction.sender.Prompter;

public class Add extends Committer {
  @Override public void Execute() {
    sieve.add(sieve.cook(committed));
  }
  public Add(Reciever reciever) {super(reciever);}
  @Override public boolean commit(Prompter.ParamsCollector element) {
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