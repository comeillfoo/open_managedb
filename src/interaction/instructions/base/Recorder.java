package interaction.instructions.base;

import interaction.customer.Reciever;
import interaction.instructions.Decree;

import java.io.PrintStream;

public abstract class Recorder extends Decree {
  protected final PrintStream printer;
  protected Recorder(Reciever reciever, PrintStream printer) {
    super(reciever);
    this.printer = printer;
  }
}
