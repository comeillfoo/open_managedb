package interaction.instructions.extended;

import interaction.customer.Receiver;
import interaction.instructions.Decree;

abstract class FilterContains extends Decree {
  protected Indicator litmus;
  protected FilterContains(Receiver receiver) {
    super(receiver);
  }
}