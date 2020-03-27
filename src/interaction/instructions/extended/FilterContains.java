package interaction.instructions.extended;
import interaction.customer.Receiver;
import interaction.instructions.Decree;
import interaction.instructions.base.Recorder;

import java.io.PrintStream;

/**
 * Асбтрактный класс-фильтратор объектов коллекции по какому-то
 * асбтрактному полю
 * @author Lenar Khannanov aka Come_1LL_F00
 * @author Anton Sushkevich aka Leargy
 */
abstract class FilterContains extends Recorder {
  protected Indicator litmus;
  protected FilterContains(Receiver receiver, PrintStream printer) {
    super(receiver, printer);
  }
}