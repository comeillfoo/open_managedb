package interaction.instructions.base;

import entity.Organization;
import interaction.customer.Receiver;
import interaction.instructions.Decree;

/**
 * Класс команд удаления элементов по ключу
 */
public class RemoveKey extends Decree {
  private Integer key;
  public RemoveKey(Receiver<Integer, Organization> receiver) {
    super(receiver);
  }
  public void openKey(Integer key) {
    this.key = key;
  }
  @Override public void execute() {
    sieve.remove(key);
  }
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "remove_key";
  public static final String BRIEF = "Удаляет элемент по [key].";
  public static final String SYNTAX = NAME + " [key]";
}