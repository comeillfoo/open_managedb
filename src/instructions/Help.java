package instructions;

public class Help implements Command {
  @Override public void Execute() {}
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
  private final String NAME = this.ToString();
  private final String BRIEF = "выводит справку по доступным командам";
  private final String SYNTAX = this.ToString();
  private final String DESCRIPTION = "Выводит справку по доступным командам.";
}