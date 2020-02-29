package interaction.instructions.base;

import interaction.customer.Receiver;
import interaction.sender.Prompter;

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
  public static final String DESCRIPTION = "Заменяет объект коллекции,соответсвующий указанному пользователем id, обновленной версией." +
                                        "\nПосле указания id пользователю предлагается ввести параметры, для формирования замены.\n" +
                                        "После конца этой процедуры, пользователю необходимо указать аргумент в фигурных скобках.";
  @Override
  protected boolean commit(Prompter.ParamsCollector element) {
    committed = element;
    return true;
  }
  public boolean commit(int id, Prompter.ParamsCollector element) {
    this.id = id;
    return commit(element);
  }
}