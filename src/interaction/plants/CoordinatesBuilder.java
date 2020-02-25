package interaction.plants;

import entity.Coordinates;
import interaction.sender.Prompter;

class CoordinatesBuilder implements Factory<Coordinates> {
  @Override
  public Coordinates make(Prompter.ParamsCollector parts) {
    long[] integers = parts.getIntegers();
    double[] fractions = parts.getFractions();
    return new Coordinates((int) integers[0], new Float(fractions[0]));
  }
}