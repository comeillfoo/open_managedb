package interaction.instructions.extended;

import interaction.customer.Receiver;
import interaction.instructions.base.Committer;

abstract class ReplaceIf extends Committer {
  protected Indicator litmus;
  protected ReplaceIf(Receiver receiver) {
    super(receiver);
  }
}