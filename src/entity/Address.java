package entity;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import javax.xml.bind.annotation.*;

/**
 * Класс, определяющий адрес организации, используется  в качестве поля элемента коллекции
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Mappable
 * @see Organizations
 * @see Organization
 * @see OrganizationType
 * @see Location
 * @see Coordinates
 */
@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
  @XmlAttribute(name = "zipcode")
  @NotNull private final String zipCode;
  /**
   * Геттер, возвращающий значение поля, хранящий почтовый индекс
   * @return String zipCode - почтовый индекс
   */
  public String getZipCode() { return zipCode; }
  @XmlElement(name = "town")
  @Nullable private final Location town;
  /**
   * Геттер, возвращающий значение поля, хранящий местонахождение города
   * @return Location town - местоположение города
   */
  public Location getTown() { return town; }

  /**
   * Стандартный конструктор по умолчанию
   * @see Address#Address(String, Location)
   */
  public Address() {
    zipCode = "";
    town = new Location(0, 0L, 0);
  }

  /**
   * Дополнительный конструктор с параметрами
   * @param zipCode String почтовый индекс
   * @param town Location местоположение города
   */
  public Address(String zipCode, Location town) {
    this.zipCode = zipCode;
    this.town = town;
  }

  @Override
  public String toString() {
    return "Address:[zipCode: " + zipCode + "; town: " + town + "]";
  }
  @Override
  public boolean equals(Object other) {
    return true;
  }
  @Override
  public int hashCode() {
    return zipCode.hashCode() * town.hashCode() % 0xdead;
  }
}