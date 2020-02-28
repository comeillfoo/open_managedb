package interaction.instructions.extended;
import interaction.customer.Reciever;
import interaction.instructions.base.Recorder;

import java.io.PrintStream;

abstract class SumOfAnnual extends Recorder {
  protected SumOfAnnual(Reciever reciever, PrintStream printer) {
    super(reciever, printer);
  }
}