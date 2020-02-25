package interaction.plants;

import entity.Address;
import entity.Coordinates;
import entity.Organization;
import entity.OrganizationType;
import interaction.sender.Prompter;

public class OrganizationBuilder implements Factory<Organization> {
  @Override
  public Organization make(Prompter.ParamsCollector parts) {
    Prompter.ParamsCollector[] internals = parts.getInternals();
    String[] lines = parts.getLines();
    long[] integers = parts.getIntegers();
    double[] fractions = parts.getFractions();
    Coordinates coordinates = new CoordinatesBuilder().make(internals[0]);
    Address officialAddress = new AddressBuilder().make(internals[1]);
    // TODO: check if absence of constant OrganizatonType
    return new Organization(lines[0], coordinates, (float) fractions[0], lines[1], (int) integers[0], OrganizationType.valueOf(lines[2]), officialAddress);
  }
}