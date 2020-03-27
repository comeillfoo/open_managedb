package interaction.customer.plants;

import entity.Address;
import entity.Coordinates;
import entity.Organization;
import entity.OrganizationType;
import interaction.sender.Prompter;

import java.util.HashSet;
import java.util.Set;

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
  public Organization make(Prompter.Junker parts) {
    if (parts == null) return new Organization();
    Prompter.Junker[] internals = parts.getInternals();
    if (internals == null) internals = new Prompter.Junker[]{null, null};
    if (internals.length < 2) {
      Prompter.Junker[] buffer = new Prompter.Junker[2];
      int i = 0;
      do {
        buffer[i] = internals[i]; i++;
      } while (i < internals.length);
      while (i < buffer.length) buffer[i++] = null;
      internals = buffer;
    }
    String[] lines = parts.getLines();
    if (lines == null) lines = new String[]{"", "", "PUBLIC"};
    if (lines.length < 3) {
      String[] buffer = new String[3];
      int i = 0;
      do {
        buffer[i] = lines[i];
        i++;
      } while (i < lines.length);
      while (i < buffer.length) buffer[i++] = "PUBLIC";
      lines = buffer;
    }
    long[] integers = parts.getIntegers();
    if (integers == null || integers.length < 1) integers = new long[]{0};
    double[] fractions = parts.getFractions();
    if (fractions == null || fractions.length < 1) fractions = new double[]{0.0};
    Coordinates coordinates = new CoordinatesBuilder().make(internals[0]);
    Address officialAddress = new AddressBuilder().make(internals[1]);
    boolean isContains = false;
    for (OrganizationType type : OrganizationType.values())
      if (type.toString().startsWith(lines[2])) {
        lines[2] = type.toString();
        isContains = true;
        break;
      }
    if (!isContains) lines[2] = OrganizationType.PUBLIC.toString();
    return new Organization(lines[0], coordinates, (float) fractions[0], lines[1], (int) integers[0], OrganizationType.valueOf(lines[2]), officialAddress);
  }
}