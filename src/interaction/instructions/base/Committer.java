package interaction.instructions.base;

import interaction.customer.Receiver;
import interaction.instructions.Decree;
import interaction.sender.Prompter;

/**
 * Абстрактный класс всех комманд манимулирующий элементом коллекцией,
 * в основном добавляет элемент в коллекцию.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Decree
 */
public abstract class Committer extends Decree {
  protected Prompter.Junker committed;

  /**
   * Стандартный конструктор из суперкласса
   * @param receiver ссылка на экземпляр {@link Receiver}
   */
  protected Committer(Receiver receiver) {
    super(receiver);
  }

  /**
   * Основной метод постановки элемента на потенциальное добавление
   * @param element сам добавляемый элемент, точнее его параметры
   * @return признак успешности постановления элемента в очередь
   */
  protected abstract boolean commit(Prompter.Junker element);
}
