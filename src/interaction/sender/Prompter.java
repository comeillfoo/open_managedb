package interaction.sender;

import interaction.instructions.Command;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

abstract class Prompter implements Invoker {
  protected final PrintStream pipe;
  protected final Scanner interrogater;
  protected final Map<String, Command> dictionary;
  public Prompter(PrintStream pipeout, InputStream pipein) {
    dictionary = new HashMap<>();
    pipe = pipeout;
    interrogater = new Scanner(pipein);
  }
  @Override public void signup(String command_name, Command instruct) {
    dictionary.put(command_name, instruct);
  }
  @Override public void invoke(String command_name) {dictionary.getOrDefault(command_name, () -> System.err.println("404")).Execute();}
  @Override public PrintStream getMainStream() {return pipe;}
}