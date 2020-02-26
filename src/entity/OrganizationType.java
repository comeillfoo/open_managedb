package entity;

import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "organizationtype")
public enum OrganizationType {
  @XmlEnumValue(value = "public")
  PUBLIC,
  @XmlEnumValue(value = "trust")
  TRUST,
  @XmlEnumValue(value = "private_limited_company")
  PRIVATE_LIMITED_COMPANY,
  @XmlEnumValue(value = "open_joint_stock_company")
  OPEN_JOINT_STOCK_COMPANY;
}