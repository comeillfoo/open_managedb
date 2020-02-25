package interaction.plants;

import interaction.sender.Prompter;

interface Factory<T> {
  <T> T make(Prompter.ParamsCollector parts);
}