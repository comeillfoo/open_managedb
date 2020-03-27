package interaction.instructions.extended.comparators;

import entity.Location;
import java.util.Comparator;

class LocationComparator implements Comparator<Location> {
  @Override
  public int compare(Location l1, Location l2) {
    return Comparator.comparing(Location::getX).thenComparing(Location::getY).thenComparing(Location::getZ).compare(l1, l2);
  }
}
