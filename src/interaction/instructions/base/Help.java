package interaction.instructions.base;

import interaction.customer.Reciever;
import interaction.instructions.extended.*;
import java.io.PrintStream;

public class Help extends Recorder {
  private final String[] pages = new String[16];

  public Help(Reciever reciever, PrintStream printer) {super(reciever, printer);}

  @Override public void Execute() {sieve.man(pages, printer);}
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
    pages[10] = makepage(FilterContains.NAME, FilterContains.BRIEF, FilterContains.SYNTAX, FilterContains.DESCRIPTION);
    pages[11] = makepage(MaxBy.NAME, MaxBy.BRIEF, MaxBy.SYNTAX, MaxBy.DESCRIPTION);
    pages[12] = makepage(RemoveThan.NAME, RemoveThan.BRIEF, RemoveThan.SYNTAX, RemoveThan.DESCRIPTION);
    pages[13] = makepage(ReplaceIf.NAME, ReplaceIf.BRIEF, ReplaceIf.SYNTAX, ReplaceIf.DESCRIPTION);
    pages[14] = makepage(SumOfAnnual.NAME, SumOfAnnual.BRIEF, SumOfAnnual.SYNTAX, SumOfAnnual.DESCRIPTION);
    pages[15] = makepage(ExecuteScript.NAME, ExecuteScript.BRIEF, ExecuteScript.SYNTAX, ExecuteScript.DESCRIPTION);
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