package interaction.instructions.extended.comparators;

import entity.Address;
import java.util.Comparator;

class AddressComparator implements Comparator<Address> {
  @Override
  public int compare(Address a1, Address a2) {
    return Comparator.comparing(Address::getZipCode)
        .thenComparing((l1, l2)->(new LocationComparator().compare(l1.getTown(), l2.getTown()))).compare(a1, a2);
  }
}
