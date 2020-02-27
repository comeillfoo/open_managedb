package interaction.instructions.extended;

import interaction.customer.Reciever;
import interaction.sender.Prompter;

abstract class RemoveThan extends ReplaceIf {
  protected RemoveThan(Reciever reciever) {
    super(reciever);
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