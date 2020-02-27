package entity;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import javax.xml.bind.annotation.*;
import java.time.ZonedDateTime;

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
  @XmlElement(name = "coordinates")
  @NotNull
  private final Coordinates coordinates;
  @XmlTransient
  @NotNull
  private final ZonedDateTime creationDate = ZonedDateTime.now();
  @XmlAttribute(name = "annual-turnover")
  private final float annualTurnover;
  @XmlElement(name = "fullname")
  @NotNull
  private final String fullname;
  @XmlAttribute(name = "employees-count")
  private final int employeesCount;
  @XmlElement(name = "organization-type", required = true)
  @Nullable
  private final OrganizationType type;
  @XmlElement(name = "official-address")
  @Nullable
  private final Address officialAddress;
  public Organization() {
    id = hashCode() * count++;
    name  = "Tune-IT";
    coordinates = new Coordinates(25, 14.2f);
    annualTurnover = 2.28f;
    fullname = "SergeBlonde";
    employeesCount = 0;
    type = OrganizationType.PUBLIC;
    officialAddress = new Address("128-147-14", new Location(25, 34L, 18));
  }
  public Organization(String name, Coordinates coordinates,
                      float annualTurnover, String fullname,
                      int employeesCount, OrganizationType type, Address officialAddress) {
    id = hashCode() * count++;
    this.name = name;
    this.coordinates = coordinates;
    this.annualTurnover = annualTurnover;
    this.fullname = fullname;
    this.employeesCount = employeesCount;
    this.type = type;
    this.officialAddress = officialAddress;

  }
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
    return 0xdead;
  }

  @Override
  public Integer getKey() {
    return id;
  }
}