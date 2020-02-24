package entity;

import com.sun.istack.internal.NotNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "coordinates")
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates {
  @XmlAttribute(name = "x")
  private final int x;
  @XmlAttribute(name = "y")
  @NotNull
  private final Float y;

  public Coordinates(int x, Float y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "Coordinates: [x: " + x + "; y: " + y + "]";
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
