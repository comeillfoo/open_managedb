package interaction.sender;

import interaction.instructions.Command;
import java.io.PrintStream;

public interface Invoker {
  void signup(String command_name, Command instruct);
  boolean scan();
  PrintStream getMainStream();
}