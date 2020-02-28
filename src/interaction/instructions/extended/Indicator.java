package interaction.instructions.extended;

import entity.Mappable;

@FunctionalInterface
public interface Indicator {
  boolean detect(Mappable<Integer> subject);
}
