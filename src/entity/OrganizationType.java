package entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Перечисление с типами компаний, изначально содержало следующие типы:
 * <ul>
 *   <li>{@link #PUBLIC} - публичная</li>
 *   <li>{@link #TRUST} - трастовая (трест)</li>
 *   <li>{@link #PRIVATE_LIMITED_COMPANY} - Закрытое Акционерное Общество (ЗАО)</li>
 *   <li>{@link #OPEN_JOINT_STOCK_COMPANY} - Открытое Акционерное Общество (ОАО)</li>
 * </ul>
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Mappable
 * @see Organizations
 * @see Organization
 * @see Coordinates
 * @see Address
 * @see Location
 */
@XmlType(name = "organization-type")
@XmlEnum(value = String.class)
public enum OrganizationType {
  @XmlEnumValue("public") PUBLIC,
  @XmlEnumValue("trust") TRUST,
  @XmlEnumValue("private-limited-company") PRIVATE_LIMITED_COMPANY,
  @XmlEnumValue("open-joint-stock-company") OPEN_JOINT_STOCK_COMPANY;
}