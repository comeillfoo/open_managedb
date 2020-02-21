package interaction.sender;

import interaction.instructions.Command;

import java.util.HashMap;
import java.util.Map;

public abstract class Prompter implements Invoker {
  private final Map<String, Command> dictionary;
  public Prompter() {
    dictionary = new HashMap<>();
  }

  public void signup(String command_name, Command instruct) {
    dictionary.put(command_name, instruct);
  }
  public void invoke(String command_name) {dictionary.getOrDefault(command_name, () -> System.err.println("404")).Execute();}
}