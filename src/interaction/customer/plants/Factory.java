package interaction.customer.plants;
import interaction.sender.Prompter;

/**
 * Обобщенный интерфейс Factory (Фабрика), не задумывался
 * как функциональный, но на данный момент имеет лишь один переопределяемый метод
 * @param <T> тип создаваемого объекта
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see OrganizationBuilder
 * @see CoordinatesBuilder
 * @see LocationBuilder
 * @see AddressBuilder
 */
interface Factory<T> {
  /**
   * Метод, создающий экземпляр типа T
   * @param parts объект сериализации, хранящий лишь неупорядоченные данные
   * @return объект типа T, где T - тип производимой продукции
   */
  <T> T make(Prompter.Junker parts);
}