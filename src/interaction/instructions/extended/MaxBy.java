package interaction.instructions.extended;

import interaction.customer.Reciever;
import interaction.instructions.Decree;

abstract class MaxBy extends Decree {
  protected MaxBy(Reciever reciever) {
    super(reciever);
  }
}