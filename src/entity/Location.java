package entity;

import com.sun.istack.internal.NotNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "location")
@XmlAccessorType(XmlAccessType.FIELD)
public class Location {
  @XmlAttribute(name = "x")
  private final long x;
  @XmlAttribute(name = "y")
  @NotNull private final Long y;
  @XmlAttribute(name = "z")
  private final double z;
  public Location() {
    x = 0L; y = 0L; z = 0;
  }
  public Location(long x, Long y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  @Override
  public String toString() {
    return "Location: [x: " + x + "; y: " + y + "; z: " + z + "]";
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