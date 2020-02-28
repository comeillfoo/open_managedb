package interaction.instructions.base;

import entity.Organization;
import interaction.customer.Reciever;
import interaction.instructions.Decree;

public class RemoveKey extends Decree {
  private Integer key;
  public RemoveKey(Reciever<Integer, Organization> reciever) {
    super(reciever);
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
  public static final String DESCRIPTION = "Удаляет элемент коллекции с указанным [key].";
}