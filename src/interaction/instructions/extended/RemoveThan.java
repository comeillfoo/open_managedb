package interaction.instructions.extended;

import interaction.customer.Receiver;
import interaction.sender.Prompter;

/**
 * Абстрактный класс, убирающий элементы больше или меньшие чем заданный
 */
abstract class RemoveThan extends ReplaceIf {
  protected RemoveThan(Receiver receiver) {
    super(receiver);
  }
  @Override
  protected boolean commit(Prompter.Junker element) {
    if (element != null) {
      committed = element;
      return true;
    }
    return false;
  }
}