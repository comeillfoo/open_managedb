package interaction.sender;

import interaction.instructions.Command;
import java.io.PrintStream;

public interface Invoker {
  void signup(String command_name, Command instruct);
  void invoke(String command_name);
  PrintStream getMainStream();
}