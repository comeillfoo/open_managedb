package interaction.instructions;
package interaction.customer.*;

public class Help implements Command {
  private final PrintStream printer;
  public Help(PrintStream printer) {this.printer = printer;}
  @Override public void Execute() {
    for (String page : pages)
      sieve.print(page);
  }
  private String makepage(String command_name, String brief, String syntax, String description) {
    StringBuilder built = new StringBuilder(">" + command_name.toUpperCase());
    built.append("name:\n");
    built.append("\t" + command_name + " -- " + brief + "\n");
    built.append("synopsys:\n");
    built.append("\t" + syntax);
    built.append("description:\n");
    built.append("\t" + description + "\n");
    return built.toString();
  }
  private final String[] pages = new String[15];
  {
    pages[0] = makepage(Add.NAME, Add.BRIEF, Add.SYNTAX, Add.DESCRIPTION);
    pages[1] = makepage(Clear.NAME, Clear.BRIEF, Clear.SYNTAX, Clear.DESCRIPTION);
    pages[2] = makepage(ExecuteScript.NAME, ExecuteScript.BRIEF, ExecuteScript.SYNTAX, ExecuteScript.DESCRIPTION);
    pages[3] = makepage(Exit.NAME, Exit.BRIEF, Exit.SYNTAX, Exit.DESCRIPTION);
    pages[4] = makepage(FilterContains.NAME, FilterContains.BRIEF, FilterContains.SYNTAX, FilterContains.DESCRIPTION);
    pages[5] = makepage(Help.NAME, Help.BRIEF, Help.SYNTAX, Help.DESCRIPTION);
    pages[6] = makepage(Info.NAME, Info.BRIEF, Info.SYNTAX, Info.DESCRIPTION);
    pages[7] = makepage(MaxBy.NAME, MaxBy.BRIEF, MaxBy.SYNTAX, MaxBy.DESCRIPTION);
    pages[8] = makepage(RemoveKey.NAME, RemoveKey.BRIEF, RemoveKey.SYNTAX, RemoveKey.DESCRIPTION);
    pages[9] = makepage(RemoveThan.NAME, RemoveThan.BRIEF, RemoveThan.SYNTAX, RemoveThan.DESCRIPTION);
    pages[10] = makepage(ReplaceIf.NAME, ReplaceIf.BRIEF, ReplaceIf.SYNTAX, ReplaceIf.DESCRIPTION);
    pages[11] = makepage(Save.NAME, Save.BRIEF, Save.SYNTAX, Save.DESCRIPTION);
    pages[12] = makepage(Show.NAME, Show.BRIEF, Show.SYNTAX, Show.DESCRIPTION);
    pages[13] = makepage(SumOfAnnual.NAME, SumOfAnnual.BRIEF, SumOfAnnual.SYNTAX, SumOfAnnual.DESCRIPTION);
    pages[14] = makepage(Update.NAME, Update.BRIEF, Update.SYNTAX, Update.DESCRIPTION);
  }
  public final static String NAME = this.ToString();
  public final static String BRIEF = "выводит справку по доступным командам";
  public final static String SYNTAX = this.ToString();
  public final static String DESCRIPTION = "Выводит справку по доступным командам.";
}