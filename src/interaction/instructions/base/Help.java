package interaction.instructions.base;

import interaction.customer.Receiver;
import interaction.instructions.extended.*;
import java.io.PrintStream;

public class Help extends Recorder {
  private final String[] pages = new String[17];

  public Help(Receiver receiver, PrintStream printer) {super(receiver, printer);}

  @Override public void execute() {sieve.man(pages, printer);}
  private String makepage(String command_name, String brief, String syntax, String description) {
    StringBuilder built = new StringBuilder("Г " + command_name.toUpperCase() + "\n");
    built.append("name:\n");
    built.append("\t" + command_name + " -- " + brief + "\n");
    built.append("synopsys:\n");
    built.append("\t" + syntax + "\n");
    built.append("description:\n");
    built.append("\t" + description + "\nL");
    return built.toString();
  }
  // initialize block
  {
    pages[0] = makepage(Add.NAME, Add.BRIEF, Add.SYNTAX, Add.DESCRIPTION);
    pages[1] = makepage(Clear.NAME, Clear.BRIEF, Clear.SYNTAX, Clear.DESCRIPTION);
    pages[2] = makepage(Exit.NAME, Exit.BRIEF, Exit.SYNTAX, Exit.DESCRIPTION);
    pages[3] = makepage(Help.NAME, Help.BRIEF, Help.SYNTAX, Help.DESCRIPTION);
    pages[4] = makepage(Info.NAME, Info.BRIEF, Info.SYNTAX, Info.DESCRIPTION);
    pages[5] = makepage(Insert.NAME, Insert.BRIEF, Insert.SYNTAX, Insert.DESCRIPTION);
    pages[6] = makepage(RemoveKey.NAME, RemoveKey.BRIEF, RemoveKey.SYNTAX, RemoveKey.DESCRIPTION);
    pages[7] = makepage(Save.NAME, Save.BRIEF, Save.SYNTAX, Save.DESCRIPTION);
    pages[8] = makepage(Show.NAME, Show.BRIEF, Show.SYNTAX, Show.DESCRIPTION);
    pages[9] = makepage(Update.NAME, Update.BRIEF, Update.SYNTAX, Update.DESCRIPTION);
    pages[10] = makepage(FilterContainsName.NAME, FilterContainsName.BRIEF, FilterContainsName.SYNTAX, FilterContainsName.DESCRIPTION);
    pages[11] = makepage(MaxByDate.NAME, MaxByDate.BRIEF, MaxByDate.SYNTAX, MaxByDate.DESCRIPTION);
    pages[12] = makepage(RemoveLower.NAME, RemoveLower.BRIEF, RemoveLower.SYNTAX, RemoveLower.DESCRIPTION);
    pages[13] = makepage(ReplaceIfLower.NAME, ReplaceIfLower.BRIEF, ReplaceIfLower.SYNTAX, ReplaceIfLower.DESCRIPTION);
    pages[14] = makepage(ReplaceIfGreater.NAME, ReplaceIfGreater.BRIEF, ReplaceIfGreater.SYNTAX, ReplaceIfGreater.DESCRIPTION);
    pages[15] = makepage(SumOfAnnualTurnover.NAME, SumOfAnnualTurnover.BRIEF, SumOfAnnualTurnover.SYNTAX, SumOfAnnualTurnover.DESCRIPTION);
    pages[16] = makepage(ExecuteScript.NAME, ExecuteScript.BRIEF, ExecuteScript.SYNTAX, ExecuteScript.DESCRIPTION);
  }

  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "help";
  public static final String BRIEF = "выводит справку по доступным командам";
  public static final String SYNTAX = NAME;
  public static final String DESCRIPTION = "Выводит справку по доступным командам.";
  @Override public int hashCode() {
    return (NAME.hashCode() + BRIEF.hashCode() + SYNTAX.hashCode() + DESCRIPTION.hashCode()) % sieve.hashCode();
  }
}