package entity;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import javax.xml.bind.annotation.*;
import java.time.ZonedDateTime;

@XmlRootElement(name="organization")
@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Mappable {
  private static int count = 0;
  @XmlAttribute(name = "id")
  private final int id; // generates automatically
  @XmlAttribute(name = "name")
  @NotNull
  private final String name;
  @XmlElement
  @NotNull
  private final Coordinates coordinates;
  @XmlElement
  @NotNull
  private final ZonedDateTime creationDate = ZonedDateTime.now();
  private final float annualTurnover;
  @NotNull
  private final String fullname;
  private final int employeesCount;
  @XmlElement
  @Nullable
  private final OrganizationType type;
  @XmlElement
  @Nullable
  private final Address officialAddress;

  public Organization(String name, Coordinates coordinates,
                      float annualTurnover, String fullname,
                      int employeesCount, OrganizationType type, Address officialAddress) {
    id = count++;
    this.name = name;
    this.coordinates = coordinates;
    this.annualTurnover = annualTurnover;
    this.fullname = fullname;
    this.employeesCount = employeesCount;
    this.type = type;
    this.officialAddress = officialAddress;

  }
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
  public int getKey() {
    return id;
  }
}

enum OrganizationType {PUBLIC, TRUST, PRIVATE_LIMITED_COMPANY, OPEN_JOINT_STOCK_COMPANY;}