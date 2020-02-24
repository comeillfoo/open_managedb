package interaction.instructions.base;

import entity.Mappable;
import interaction.customer.Reciever;

public class Insert<K> extends Committer {
  private K key;

  public Insert(Reciever reciever) {
    super(reciever);
  }

  public boolean commit(K key, Mappable element) {
    this.key = key;
    committed = element;
    return true;
  }

  @Override public void Execute() { sieve.add(key, committed); }
  @Override public String toString() {return NAME + " : " + SYNTAX;}

  public static final String NAME = "insert";
  public static final String BRIEF = "";
  public static final String SYNTAX = NAME + " [key] {element}";
  public static final String DESCRIPTION = "";
}
