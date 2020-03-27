package interaction.instructions.base;

import interaction.customer.Receiver;
import interaction.sender.Prompter;

/**
 * Класс команды вставки кастомного элемента в коллекцию по заданному ключу
 */
public class Insert extends Committer {
  private Integer key;
  public Insert(Receiver receiver) {
    super(receiver);
  }
  @Override protected boolean commit(Prompter.Junker element) {
    committed = element;
    return true;
  }
  public boolean commit(Integer key, Prompter.Junker element) {
    this.key = key;
    return commit(element);
  }
  @Override public void execute() { sieve.add(key, sieve.cook(committed)); }
  @Override public String toString() {return NAME + " : " + SYNTAX;}

  public static final String NAME = "insert";
  public static final String BRIEF = "Добавляет элемент с указанным [key] в колекцию.";
  public static final String SYNTAX = NAME + " [key] {element}";
}
