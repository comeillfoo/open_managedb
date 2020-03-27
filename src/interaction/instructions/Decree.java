package interaction.instructions;

import interaction.customer.Receiver;

/**
 * Абстрактный класс комманд, расширяющий интерфейс
 * комманд основными полями и данными о команде.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Command
 */
public abstract class Decree implements Command {
  protected final Receiver sieve;
  @Override public String toString() {
    return NAME + " : " + SYNTAX;
  }
  /**
   * Стандартный конструктор, устанавливающий ссылку на {@link Receiver}
   * @param receiver ссылка на экземпляр, управляющий коллекцией
   */
  protected Decree(Receiver receiver) {sieve = receiver;}
  protected static final String NAME = null;
  protected static final String BRIEF = null;
  protected static final String SYNTAX = null;
}
