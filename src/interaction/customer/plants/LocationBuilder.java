package interaction.customer.plants;

import entity.Location;
import interaction.sender.Prompter;

/**
 * Класс-фабрика положений
 * Создает из объекта сериализации место,
 * путем разбора его отдельных компонент.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Factory
 * @see Location
 * @see AddressBuilder
 * @see CoordinatesBuilder
 * @see OrganizationBuilder
 */
class LocationBuilder implements Factory<Location> {
  /**
   * Метод, переопределенный из обобщенного интерфейса Factory
   * Создает экземпляр типа T, в данном случае Location
   * @param parts объект сериализации, хранящий лишь неупорядоченные данные
   * @return объект типа Location - место
   */
  @Override
  public Location make(Prompter.Junker parts) {
    if (parts == null) return null;
    long[] integers = parts.getIntegers();
    double[] fractions = parts.getFractions();
    return new Location(integers[0], new Long(integers[1]), fractions[0]);
  }
}