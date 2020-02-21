package interaction.instructions.base;

import entity.Mappable;
import interaction.customer.Reciever;
import interaction.instructions.Decree;

abstract class Committer extends Decree {
  protected Mappable committed;
  protected Committer(Reciever reciever) {
    super(reciever);
  }
}
