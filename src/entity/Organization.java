package entity;

public class Organization {
  private final int id;
  private final String name;
  private final Coordinates coordinates;
  private final ZonedDateTime creationDate;
  private final float annualTurnover;
  private final String fullname;
  private final int employeesCount;
  private final OrganisationType type;
  private final Address officialAddress;
  public String ToString() {

  }

  public boolean equals(Object other) {

  }

  public int hashCode() {

  }
}

enum OrganisationType {PUBLIC, TRUST, PRIVATE_LIMITED_COMPANY, OPEN_JOINT_STOCK_COMPANY;}