package interaction.instructions.extended;

import interaction.customer.Receiver;
import interaction.sender.Prompter;

abstract class RemoveThan extends ReplaceIf {
  protected RemoveThan(Receiver receiver) {
    super(receiver);
  }

  @Override
  protected boolean commit(Prompter.ParamsCollector element) {
    if (element != null) {
      committed = element;
      return true;
    }
    return false;
  }
}