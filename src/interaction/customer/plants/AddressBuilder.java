package interaction.customer.plants;

import entity.Address;
import entity.Location;
import interaction.sender.Prompter;

/**
 * Класс-фабрика адресов
 * Создает из объекта сериализации адрес,
 * путем разбора его отдельных компонент.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Factory
 * @see Address
 * @see Location
 * @see LocationBuilder
 * @see CoordinatesBuilder
 * @see OrganizationBuilder
 */
class AddressBuilder implements Factory<Address> {
  /**
   * Метод, переопределенный из обобщенного интерфейса Factory
   * Создает экземпляр типа T, в данном случае Address
   * @param parts объект сериализации, хранящий лишь неупорядоченные данные
   * @return объект типа Address - адрес
   */
  @Override
  public Address make(Prompter.Junker parts) {
    if (parts == null) return null;
    String[] lines = parts.getLines();
    Location town = new LocationBuilder().make(parts.getInternals()[0]);
    return new Address(lines[0], town);
  }
}