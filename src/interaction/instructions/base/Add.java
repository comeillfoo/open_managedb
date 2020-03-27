package interaction.instructions.base;

import interaction.customer.Receiver;
import interaction.sender.Prompter;

public class Add extends Committer {  //Command to work.
  @Override public void execute() {
    sieve.add(sieve.cook(committed));
  }
  public Add(Receiver receiver) {super(receiver);}
  @Override public boolean commit(Prompter.Junker element) {
    committed = element;
    return true;
  }
  @Override public String toString() {
    return NAME + " : " + SYNTAX;
  }
  public static final String NAME = "add";
  public static final String BRIEF = "добавляет новый элемент в коллекцию";
  public static final String SYNTAX = NAME + " {element}";
}