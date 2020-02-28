package interaction.instructions.extended;

import interaction.customer.Receiver;

public class FilterContainsName extends FilterContains {
  private String name = "";
  public FilterContainsName(Receiver receiver) {
    super(receiver);
    litmus = (subject) -> (name.equals("")? true : name.equals(subject.getName()));
  }
  public void searchFor(String name) {
    if (name == null)
      this.name = "";
    else this.name = name;
  }
  @Override public void execute() { sieve.survey(litmus); }
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "filter_contains_name";
  public static final String BRIEF = "";
  public static final String SYNTAX = NAME + " [name]";
  public static final String DESCRIPTION = "";
}
