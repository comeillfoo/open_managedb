package interaction.instructions.extended;

import interaction.customer.Reciever;
import interaction.instructions.base.Committer;

abstract class ReplaceIf extends Committer {
  protected Indicator litmus;
  protected ReplaceIf(Reciever reciever) {
    super(reciever);
  }
}