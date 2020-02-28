package interaction.instructions.base;

import interaction.customer.Receiver;
import interaction.instructions.Decree;
import interaction.sender.Prompter;

public abstract class Committer extends Decree {
  protected Prompter.ParamsCollector committed;
  protected Committer(Receiver receiver) {
    super(receiver);
  }
  protected abstract boolean commit(Prompter.ParamsCollector element);
}
