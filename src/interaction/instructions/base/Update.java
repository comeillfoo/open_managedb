package interaction.instructions.base;

import interaction.customer.Receiver;
import interaction.sender.Prompter;

/**
 * Класс команды обновления элемента коллекции
 */
public class Update extends Committer {
  private int id;
  public Update(Receiver receiver) {
    super(receiver);
  }
  @Override public void execute() {sieve.add(sieve.search(id), sieve.cook(committed));}
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "update";
  public static final String BRIEF = "Обновляет элемент коллекции.";
  public static final String SYNTAX = NAME + " id {element}";
  @Override
  protected boolean commit(Prompter.Junker element) {
    committed = element;
    return true;
  }
  public boolean commit(int id, Prompter.Junker element) {
    if (sieve.search(id) != null) {
      this.id = id;
      return commit(element);
    } else return false;
  }
}