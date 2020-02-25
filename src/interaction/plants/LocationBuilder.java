package interaction.plants;

import entity.Location;
import interaction.sender.Prompter;

class LocationBuilder implements Factory<Location> {
  @Override
  public Location make(Prompter.ParamsCollector parts) {
    long[] integers = parts.getIntegers();
    double[] fractions = parts.getFractions();
    return new Location(integers[0], new Long(integers[1]), fractions[0]);
  }
}