package interaction.instructions.extended.comparators;

import entity.Organization;
import java.util.Comparator;

public class OrganizationComparator implements Comparator<Organization> {
  @Override
  public int compare(Organization o1, Organization o2) {
    return Comparator.comparing(Organization::getName).thenComparing(Organization::getFullname)
        .thenComparing(Organization::getCreationDate)
        .thenComparing(Organization::getEmployees)
        .thenComparing(Organization::getAnnualTurnOver)
        .thenComparing(Organization::getType)
        .thenComparing((org1, org2)->( new CoordinatesComparator().compare(org1.getCoordinates(), org2.getCoordinates())))
        .thenComparing((org1, org2)->( new AddressComparator().compare(org1.getAddress(), org2.getAddress()))).compare(o1, o2);
  }
}
