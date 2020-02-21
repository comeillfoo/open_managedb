package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Organizations {
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
