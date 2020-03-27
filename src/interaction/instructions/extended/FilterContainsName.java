package interaction.instructions.extended;

import interaction.customer.Receiver;

import java.io.PrintStream;

/**
 * Класс команды-фильтратора, которая отбрасывает элементы, не содержащие заданного имени
 */
public class FilterContainsName extends FilterContains {
  private String name = "";
  public FilterContainsName(Receiver receiver, PrintStream printer) {
    super(receiver, printer);
    litmus = (subject) -> (name.equals("") || subject.getName().contains(name));
  }
  public void searchFor(String name) {
    if (name == null)
      this.name = "";
    else this.name = name;
  }
  @Override public void execute() { printer.println(sieve.survey(litmus)); }
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "filter_contains_name";
  public static final String BRIEF = "поиск объекат по имени";
  public static final String SYNTAX = NAME + " [name]";
}
