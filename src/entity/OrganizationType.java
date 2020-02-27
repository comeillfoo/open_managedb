package entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "organization-type")
@XmlEnum(value = String.class)
public enum OrganizationType {
  @XmlEnumValue("public") PUBLIC,
  @XmlEnumValue("trust") TRUST,
  @XmlEnumValue("private-limited-company") PRIVATE_LIMITED_COMPANY,
  @XmlEnumValue("open-joint-stock-company") OPEN_JOINT_STOCK_COMPANY;
}