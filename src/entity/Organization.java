package entity;

import java.time.ZonedDateTime;
import java.

@XmlRootElement(name="organization")
public class Organization {
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
                      int employeesCount, OrganisationType type, Address officialAddress) {
    this.name = name;
    this.creationDate = ZonedDateTime.;
    this.annualTurnover = annualTurnover;
    this.fullname = fullname;
    this.employeesCount = employeesCount;
    this.type = type;
    this.officialAddress = officialAddress;

  }
  @Override
  public String ToString() {
    return "Organization[id: " + id + "; name: " + name + "; coordinates: " + coordinates
        + "; creationDate: " + creationDate + "; annualTurnover: " + annualTurnover
        + "; fullname: " + fullname + "; employeesCount: " + employeesCount
        + "; type: " + type + "; officialAddress: " + officialAddress + "]";
  }
  @Override
  public boolean equals(Object other) {

  }
  @Override
  public int hashCode() {
    return 0;
  }
}

enum OrganizationType {PUBLIC, TRUST, PRIVATE_LIMITED_COMPANY, OPEN_JOINT_STOCK_COMPANY;}