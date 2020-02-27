package interaction.instructions.extended;

import interaction.customer.Reciever;
import interaction.instructions.Decree;

abstract class FilterContains extends Decree {
  protected Indicator litmus;
  protected FilterContains(Reciever reciever) {
    super(reciever);
  }
}