package interaction.instructions.base;

import entity.Mappable;
import interaction.customer.Reciever;

public class Update extends Committer {

  public Update(Reciever reciever) {
    super(reciever);
  }

  public boolean commit(Mappable element) {
    committed = element;
    return true;
  }

  @Override
  public void Execute() {
    sieve.update(committed);
  }
  @Override
  public String toString() {
    return NAME + " : " + SYNTAX;
  }

  public static final String NAME = "update";
  public static final String BRIEF = "Обновляет элемент коллекции.";
  public static final String SYNTAX = NAME + " id {element}";
  public static final String DESCRIPTION = "Заменяет объект коллекции,соответсвующий указанному пользователем id, обновленной версией." +
                                        "\nПосле указания id пользователю предлагается ввести параметры, для формирования замены.\n" +
                                        "После конца этой процедуры, пользователю необходимо указать аргумент в фигурных скобках.";
}