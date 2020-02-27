package interaction.instructions.base;

import interaction.customer.Reciever;
import interaction.sender.Prompter;

public class Insert extends Committer {
  private Integer key;
  public Insert(Reciever reciever) {
    super(reciever);
  }
  @Override protected boolean commit(Prompter.ParamsCollector element) {
    committed = element;
    return true;
  }
  public boolean commit(Integer key, Prompter.ParamsCollector element) {
    this.key = key;
    return commit(element);
  }
  @Override public void execute() { sieve.add(key, sieve.cook(committed)); }
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "insert";
  public static final String BRIEF = "";
  public static final String SYNTAX = NAME + " [key] {element}";
  public static final String DESCRIPTION = "";
}
