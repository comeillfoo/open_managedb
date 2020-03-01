package interaction.sender;

@FunctionalInterface
interface NumChecker<T extends Number> {
  boolean check(T number);
}
