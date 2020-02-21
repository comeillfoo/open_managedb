package entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@XmlRootElement(name = "organizations")
@XmlAccessorType(XmlAccessType.FIELD)
public class Organizations {
  @XmlElement(name = "organization")
  private final List<Organization> companies;
  public Organizations(List<Organization> companies) {
    this.companies = companies;
  }
  public Organizations(Map<Integer, Organization> companies) {
    this.companies = new ArrayList<>(companies.values());
  }
  public List<Organization> getCompanies() {
    return companies;
  }
}
