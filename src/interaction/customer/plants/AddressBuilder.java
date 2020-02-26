package interaction.customer.plants;

import entity.Address;
import entity.Location;
import interaction.sender.Prompter;

class AddressBuilder implements Factory<Address> {
  @Override
  public Address make(Prompter.ParamsCollector parts) {
    String[] lines = parts.getLines();
    Location town = new LocationBuilder().make(parts.getInternals()[0]);
    return new Address(lines[0], town);
  }
}