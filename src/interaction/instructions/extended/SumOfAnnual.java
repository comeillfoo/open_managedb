package interaction.instructions.extended;
import interaction.customer.Receiver;
import interaction.instructions.base.Recorder;
import java.io.PrintStream;

/**
 * Абстрактный класс подсчета сумм
 */
abstract class SumOfAnnual extends Recorder {
  protected SumOfAnnual(Receiver receiver, PrintStream printer) {
    super(receiver, printer);
  }
}