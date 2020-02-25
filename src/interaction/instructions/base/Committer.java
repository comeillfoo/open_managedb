package interaction.instructions.base;

import interaction.customer.Reciever;
import interaction.instructions.Decree;
import interaction.sender.Prompter;

public abstract class Committer extends Decree {
  protected Prompter.ParamsCollector committed;
  protected Committer(Reciever reciever) {
    super(reciever);
  }
  protected abstract boolean commit(Prompter.ParamsCollector element);
}
