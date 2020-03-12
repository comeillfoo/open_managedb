package interaction.sender;

/**
 * Обобщенный функциональный интерйфейс для проверки чисел на соответсвие
 * некоторому условию, которое определяется конкретной реализацией (классом), анонимным классом
 * или лямбда-выражением, но в основном последним.
 * @param <T> параметр наследующий интерфейс Number, чтобы проверять именно числа
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
@FunctionalInterface
interface NumChecker<T extends Number> {
  /**
   * Метод, проверящий число на соответствие условию
   * и возвращает соответствующее булевое значение.
   * @param number число которое проверяется на соответствие
   * @return boolean true - если число соответсвует условию и false - если нет
   */
  boolean check(T number);
}
