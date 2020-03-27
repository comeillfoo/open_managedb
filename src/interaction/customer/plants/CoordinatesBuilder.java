package interaction.customer.plants;

import entity.Coordinates;
import interaction.sender.Prompter;

/**
 * Класс-фабрика координат
 * Создает из объекта сериализации геометку,
 * путем разбора его отдельных компонент.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Factory
 * @see Coordinates
 * @see LocationBuilder
 * @see AddressBuilder
 * @see OrganizationBuilder
 */
class CoordinatesBuilder implements Factory<Coordinates> {
  /**
   * Метод, переопределенный из обобщенного интерфейса Factory
   * Создает экземпляр типа T, в данном случае Coordinates
   * @param parts объект сериализации, хранящий лишь неупорядоченные данные
   * @return объект типа Coordinates - местоположение
   */
  @Override
  public Coordinates make(Prompter.Junker parts) {
    if (parts == null) return new Coordinates();
    long[] integers = parts.getIntegers();
    double[] fractions = parts.getFractions();
    return new Coordinates((int) integers[0], new Float(fractions[0]));
  }
}