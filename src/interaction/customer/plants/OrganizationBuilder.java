package interaction.customer.plants;

import entity.Address;
import entity.Coordinates;
import entity.Organization;
import entity.OrganizationType;
import interaction.sender.Prompter;

/**
 * Класс-фабрика организаций
 * Создает из объекта сериализации организацию,
 * путем разбора его отдельных компонент.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Factory
 * @see LocationBuilder
 * @see AddressBuilder
 * @see CoordinatesBuilder
 * @see Address
 * @see Coordinates
 * @see Organization
 */
public class OrganizationBuilder implements Factory<Organization> {
  /**
   * Метод, переопределенный из обобщенного интерфейса Factory
   * Создает экземпляр типа T, в данном случае Organization
   * @param parts объект сериализации, хранящий лишь неупорядоченные данные
   * @return объект типа Organization - организация
   */
  @Override
  public Organization make(Prompter.ParamsCollector parts) {
    Prompter.ParamsCollector[] internals = parts.getInternals();
    String[] lines = parts.getLines();
    long[] integers = parts.getIntegers();
    double[] fractions = parts.getFractions();
    Coordinates coordinates = new CoordinatesBuilder().make(internals[0]);
    Address officialAddress = new AddressBuilder().make(internals[1]);
    // TODO: check if absence of constant OrganizatonType
    return new Organization(lines[0], coordinates, (float) fractions[0], lines[1], (int) integers[0], OrganizationType.valueOf(lines[2]), officialAddress);
  }
}