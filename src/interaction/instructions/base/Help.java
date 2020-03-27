package interaction.instructions.base;

import interaction.customer.Receiver;
import interaction.instructions.extended.*;
import java.io.PrintStream;

/**
 * Класс команды вывода справки по всем доступным командам
 */
public class Help extends Recorder {
  private final String[] pages = new String[16];
  public Help(Receiver receiver, PrintStream printer) {super(receiver, printer);}
  @Override public void execute() {sieve.man(pages, printer);}
  private String makepage(String command_name, String brief, String syntax) {
    StringBuilder built = new StringBuilder(command_name.toUpperCase() + ":\n");
    built.append("name: " + command_name + " -- " + brief + "\n");
    built.append("synopsys: " + syntax + "\n");
    return built.toString();
  }
  // initialize block
  {
    pages[0] = makepage(Clear.NAME, Clear.BRIEF, Clear.SYNTAX);
    pages[1] = makepage(Exit.NAME, Exit.BRIEF, Exit.SYNTAX);
    pages[2] = makepage(Help.NAME, Help.BRIEF, Help.SYNTAX);
    pages[3] = makepage(Info.NAME, Info.BRIEF, Info.SYNTAX);
    pages[4] = makepage(Insert.NAME, Insert.BRIEF, Insert.SYNTAX);
    pages[5] = makepage(RemoveKey.NAME, RemoveKey.BRIEF, RemoveKey.SYNTAX);
    pages[6] = makepage(Save.NAME, Save.BRIEF, Save.SYNTAX);
    pages[7] = makepage(Show.NAME, Show.BRIEF, Show.SYNTAX);
    pages[8] = makepage(Update.NAME, Update.BRIEF, Update.SYNTAX);
    pages[9] = makepage(ExecuteScript.NAME, ExecuteScript.BRIEF, ExecuteScript.SYNTAX);
    pages[10] = makepage(FilterContainsName.NAME, FilterContainsName.BRIEF, FilterContainsName.SYNTAX);
    pages[11] = makepage(MaxByDate.NAME, MaxByDate.BRIEF, MaxByDate.SYNTAX);
    pages[12] = makepage(RemoveLower.NAME, RemoveLower.BRIEF, RemoveLower.SYNTAX);
    pages[13] = makepage(ReplaceIfLower.NAME, ReplaceIfLower.BRIEF, ReplaceIfLower.SYNTAX);
    pages[14] = makepage(ReplaceIfGreater.NAME, ReplaceIfGreater.BRIEF, ReplaceIfGreater.SYNTAX);
    pages[15] = makepage(SumOfAnnualTurnover.NAME, SumOfAnnualTurnover.BRIEF, SumOfAnnualTurnover.SYNTAX);
  }
  @Override public String toString() {return NAME + " : " + SYNTAX;}
  public static final String NAME = "help";
  public static final String BRIEF = "выводит справку по доступным командам";
  public static final String SYNTAX = NAME;
  @Override public int hashCode() {
    return (NAME.hashCode() + BRIEF.hashCode() + SYNTAX.hashCode()) % sieve.hashCode();
  }
}