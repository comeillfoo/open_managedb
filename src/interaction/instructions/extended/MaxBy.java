package interaction.instructions.extended;

import interaction.customer.Receiver;
import interaction.instructions.Decree;

abstract class MaxBy extends Decree {
  protected MaxBy(Receiver receiver) {
    super(receiver);
  }
}