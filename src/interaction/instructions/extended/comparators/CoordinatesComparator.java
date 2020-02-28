package interaction.instructions.extended.comparators;

import entity.Coordinates;
import java.util.Comparator;

class CoordinatesComparator implements Comparator<Coordinates> {
  @Override
  public int compare(Coordinates c1, Coordinates c2) {
    return Comparator.comparing(Coordinates::getX).thenComparing(Coordinates::getY).compare(c1, c2);
  }

}
