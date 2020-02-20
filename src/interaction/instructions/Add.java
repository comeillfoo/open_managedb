package interaction.instructions;

public class Add extends Instruction {
  private final Managable element;
  @Override public void Execute() {
    sieve.add(element);
  }
  public Add(Managable element, Reciever reciever) {
    super(reciever);
    this.element = element;
  }
  public String ToString() {
    return "add";
  }
  public final static String NAME = this.ToString();
  public final static String BRIEF = "добавляет новый элемент в коллекцию";
  public final static String SYNTAX = this.ToString() + " {element}";
  public final static String DESCRIPTION = "Аргумент в фигурных скобках указывается после\n\t" +
      "ввода команды отдельно по приглашении ко вводу\n\t" +
      "всех требуемых полей.";
}