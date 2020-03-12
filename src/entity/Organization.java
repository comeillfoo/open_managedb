package entity;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import javax.xml.bind.annotation.*;
import java.time.ZonedDateTime;

/**
 * Основной класс, выполняющий роль элемента коллекции
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Mappable
 * @see Organizations
 * @see OrganizationType
 * @see Location
 * @see Coordinates
 * @see Address
 * @see interaction.customer.plants.OrganizationBuilder
 */
@XmlRootElement(name = "organization")
@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Mappable<Integer> {
  @XmlTransient
  private static int count = 1;
  @XmlAttribute(name = "id")
  private final int id; // generates automatically
  @XmlAttribute(name = "name")
  @NotNull
  private final String name;

  /**
   * Геттер для получения имени элемента,
   * написан лишь, чтобы корректно работала библиотека JAXB
   * (переопределен из интерфейса Mappable)
   * @return String name название организации
   */
  @Override public String getName() { return name; }
  @XmlElement(name = "coordinates")
  @NotNull
  private final Coordinates coordinates;

  /**
   * Геттер для получения имени элемента,
   * написан лишь, чтобы корректно работала библиотека JAXB
   * (переопределен из интерфейса Mappble)
   * @return String name название организации
   */
  public Coordinates getCoordinates() { return coordinates; }
  @XmlTransient
  @NotNull
  private final ZonedDateTime creationDate = ZonedDateTime.now();

  /**
   * Геттер для получения имени элемента,
   * написан лишь, чтобы корректно работала библиотека JAXB
   * (переопределен из интерфейса Mappble)
   * @return String name название организации
   */
  public ZonedDateTime getCreationDate() { return creationDate; }

  @XmlAttribute(name = "annual-turnover")
  private final float annualTurnover;

  /**
   * Геттер для получения имени элемента,
   * написан лишь, чтобы корректно работала библиотека JAXB
   * (переопределен из интерфейса Mappble)
   * @return String name название организации
   */
  public float getAnnualTurnOver() { return annualTurnover; }

  @XmlElement(name = "fullname")
  @NotNull
  private final String fullname;

  /**
   * Геттер для получения имени элемента,
   * написан лишь, чтобы корректно работала библиотека JAXB
   * (переопределен из интерфейса Mappble)
   * @return String name название организации
   */
  public String getFullname() { return fullname; }

  @XmlAttribute(name = "employees-count")
  private final int employeesCount;

  /**
   * Геттер для получения имени элемента,
   * написан лишь, чтобы корректно работала библиотека JAXB
   * (переопределен из интерфейса Mappble)
   * @return String name название организации
   */
  public int getEmployees() { return employeesCount; }

  @XmlElement(name = "organization-type", required = true)
  @Nullable
  private final OrganizationType type;

  /**
   * Геттер для получения имени элемента,
   * написан лишь, чтобы корректно работала библиотека JAXB
   * (переопределен из интерфейса Mappble)
   * @return String name название организации
   */
  public OrganizationType getType() { return type; }

  @XmlElement(name = "official-address")
  @Nullable
  private final Address officialAddress;

  /**
   * Геттер для получения имени элемента,
   * написан лишь, чтобы корректно работала библиотека JAXB
   * (переопределен из интерфейса Mappble)
   * @return String name название организации
   */
  public Address getAddress() { return officialAddress; }

  /**
   * Стадартный конструктор без параметров, создан
   * по требованию библиотеки JAXB
   * @see Organization#Organization(String, Coordinates, float, String, int, OrganizationType, Address)
   */
  public Organization() {
    name  = "Sample Organization";
    coordinates = new Coordinates();
    annualTurnover = 0;
    fullname = "sample organization pattern";
    employeesCount = 0;
    type = OrganizationType.PUBLIC;
    officialAddress = new Address();
    id = hashCode() + count++;
  }

  /**
   * Основной конструктор с параметрами, используется в фабрике организаций
   * @param name название организации String
   * @param coordinates местоположение организации относительно аддреса Coordinates
   * @param annualTurnover ежегодный оборот организации float
   * @param fullname полное наименование организации String
   * @param employeesCount число сотрудников int
   * @param type тип организации OrganizationType
   * @param officialAddress оффициальный аддресс Address
   */
  public Organization(String name, Coordinates coordinates,
                      float annualTurnover, String fullname,
                      int employeesCount, OrganizationType type, Address officialAddress) {
    this.name = name;
    this.coordinates = coordinates;
    this.annualTurnover = annualTurnover;
    this.fullname = fullname;
    this.employeesCount = employeesCount;
    this.type = type;
    this.officialAddress = officialAddress;
    id = hashCode() + count++;
  }
  /**
   *
   * @return
   */
  public int getID() {return id;}
  @Override
  public String toString() {
    return "Organization[id: " + id + "; name: " + name + "; coordinates: " + coordinates
        + "; creationDate: " + creationDate + "; annualTurnover: " + annualTurnover
        + "; fullname: " + fullname + "; employeesCount: " + employeesCount
        + "; type: " + type + "; officialAddress: " + officialAddress + "]";
  }
  @Override
  public boolean equals(Object other) {
    return true;
  }
  @Override
  public int hashCode() {
    return (int)((name.hashCode() + fullname.hashCode()) * (annualTurnover % employeesCount) + creationDate.hashCode() + coordinates.hashCode() + type.hashCode() + officialAddress.hashCode()) % 0xdead;
  }

  /**
   *
   * @return
   */
  @Override
  public Integer getKey() {
    return id;
  }
}