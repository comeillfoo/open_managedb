package interaction.instructions.extended;

import interaction.customer.Receiver;
import interaction.instructions.Decree;

/**
 * Абстрактный класс команды, находящий максимум по абстрактной переменной
 */
abstract class MaxBy extends Decree {
  protected MaxBy(Receiver receiver) {
    super(receiver);
  }
}