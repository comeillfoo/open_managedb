package interaction.instructions.base;

import entity.Mappable;
import interaction.customer.Reciever;
import interaction.instructions.Command;

public class Add implements Command {
  private final Reciever sieve;
  private final Mappable element;
  @Override public void Execute() {
    sieve.add(element);
  }
  public Add(Mappable element, Reciever reciever) {
    sieve = reciever;
    this.element = element;
  }
  @Override public String toString() {
    return "add";
  }
  public static final String NAME = toString();
  public static final String BRIEF = "добавляет новый элемент в коллекцию";
  public static final String SYNTAX = toString() + " {element}";
  public static final String DESCRIPTION = "Аргумент в фигурных скобках указывается после\n\t" +
      "ввода команды отдельно по приглашении ко вводу\n\t" +
      "всех требуемых полей.";
}