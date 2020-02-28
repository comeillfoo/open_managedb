package interaction.instructions.base;

import interaction.customer.Receiver;
import interaction.instructions.Decree;

import java.io.PrintStream;

public abstract class Recorder extends Decree {
  protected final PrintStream printer;
  protected Recorder(Receiver receiver, PrintStream printer) {
    super(receiver);
    this.printer = printer;
  }
}
