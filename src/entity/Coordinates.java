package entity;

import com.sun.istack.internal.NotNull;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Класс, определяющий координаты организации, используется как составное поле в элементе коллекции
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Mappable
 * @see Organizations
 * @see Organization
 * @see OrganizationType
 * @see Location
 * @see Address
 */
@XmlRootElement(name = "coordinates")
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates {
  @XmlAttribute(name = "x")
  private final int x;
  /**
   * Геттер для получения значения координаты X,
   * написан лишь, чтобы корректно работала библиотека JAXB
   * @return int x - целочисленная координата X
   */
  public int getX() {return x;}
  @XmlAttribute(name = "y")
  @NotNull private final Float y;
  /**
   * Геттер для получения значения координаты Y,
   * написан лишь, чтобы корректно работала библиотека JAXB
   * @return Float y - координата Y, представляющая собой число с плавающей точкой
   * <span>
   *  На самом деле это ссылка на float,
   *  однако (вследствие автоупаковок и распаковок)
   *  волноваться не о чем.
   * </span>
   */
  public float getY() {return y;}

  /**
   * Стандартный конструктор без параметров
   * @see Coordinates#Coordinates(int, Float)
   */
  public Coordinates() {
    x = 0;
    y = 0f;
  }

  /**
   * Основной конструктор с параметрами
   * @param x int - целочисленная координата по X
   * @param y Float число с плавающей точкой - координата Y
   */
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
