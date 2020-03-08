package entity;

import com.sun.istack.internal.NotNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Класс, определяющий местонахождение по нескольким кооржинатам
 * Используется, как поле элемента коллекции
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Mappable
 * @see Organizations
 * @see Organization
 * @see OrganizationType
 * @see Coordinates
 * @see Address
 */
@XmlRootElement(name = "location")
@XmlAccessorType(XmlAccessType.FIELD)
public class Location {
  @XmlAttribute(name = "x")
  private final long x;
  /**
   * Геттер для получения значения координаты X,
   * написан лишь, чтобы корректно работала библиотека JAXB
   * @return long x - целочисленная координата x
   */
  public long getX() { return x; }
  @XmlAttribute(name = "y")
  @NotNull private final Long y;
  /**
   * Геттер для получения значения координаты Y,
   * написан лишь, чтобы корректно работала библиотека JAXB
   * @return Long y - целочисленная координата по Y.
   * <span>
   *   На самом деле это ссылка на long,
   *   однако (вследствие автоупаковок и распаковок)
   *   волноваться не о чем
   * </span>
   */
  public long getY() { return y; }
  @XmlAttribute(name = "z")
  private final double z;
  /**
   * Геттер для получения значения координаты Z,
   * написан лишь, чтобы корректно работала библиотека JAXB
   * @return double z - координата z, представляющая число с плавающей точкой
   */
  public double getZ() { return z; }

  /**
   * Стандартный конструктор без параметров
   * @see Location#Location(long, Long, double)
   */
  public Location() {
    x = 0L; y = 0L; z = 0;
  }

  /**
   * Основной конструктор с параметрами
   * @param x long координата по X
   * @param y Long координата по Y
   * @param z double координата по Z
   */
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
    return (int)(x + y + z) % 0xdead;
  }
}