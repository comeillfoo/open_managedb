package entity;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
  @XmlAttribute(name = "zipcode")
  @NotNull private final String zipCode;
  public String getZipCode() { return zipCode; }
  @XmlElement(name = "town")
  @Nullable private final Location town;
  public Location getTown() { return town; }
  public Address() {
    zipCode = "";
    town = new Location(0, 0L, 0);
  }
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
    return 0xdead;
  }
}