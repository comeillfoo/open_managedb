package interaction.sender;

import exceptions.InvalidClassNameException;

import java.io.InputStream;
import java.io.PrintStream;

public final class ConsolePrompter extends Prompter {
  // initialize block
  {
    
  }
  public ConsolePrompter(PrintStream pipeout, InputStream pipein) {
    super(pipeout, pipein);
  }
  private class ParamsCollector {
    private final ParamsCollector[] internals;
    private final long[] integers;
    private final double[] fractions;
    private final String[] lines;
    private ParamsCollector(ParamsCollector[] internals, long[] integers, double[] fractions, String[] lines) {
      this.internals = internals;
      this.integers = integers;
      this.fractions = fractions;
      this.lines = lines;
    }
  }
  private ParamsCollector getParams(String className) throws InvalidClassNameException {
    switch (className) {
      case "Organization": return getOrganization();
      case "Coordinates": return getCoordinates();
      case "Address": return getAddress();
      case "Location": return getLocation();
      default: throw new InvalidClassNameException();
    }
  }
  private ParamsCollector getOrganization() {
    pipe.print("Enter string Organization.name: ");
    String name = interrogater.nextLine();
    pipe.println("Entering Organization.coordinates as Coordinates:");
    ParamsCollector coordinates = getCoordinates();
    pipe.print("Enter some float fraction Organization.annualTurnover: ");
    float annualTurnover = interrogater.nextFloat();
    pipe.print("Enter string Organization.fullname: ");
    String fullname = interrogater.nextLine();
    pipe.print("Enter an integer Organization.employeesCount: ");
    int employeesCount = interrogater.nextInt();
    pipe.println("Elect OrganizationType.type: ");
    pipe.println("\t:: ");
    pipe.println("Entering Organization.officialAddress as Address:");
    ParamsCollector officialAddress = getAddress();
    return new ParamsCollector(
        new ParamsCollector[]{coordinates, officialAddress},
        new long[]{(long) employeesCount},
        new double[]{(double) annualTurnover},
        new String[]{name, fullname});
  }
  private ParamsCollector getCoordinates() {
    pipe.print("Enter an integer Coordinates.x: ");
    int x = interrogater.nextInt();
    pipe.print("Enter a float Coordinates.y: ");
    Float y = interrogater.nextFloat();
    return new ParamsCollector(null, new long[]{(long) x}, new double[]{(double) y}, null);
  }
  private ParamsCollector getAddress() {
    pipe.print("Enter string Address.zipCode: ");
    String zipCode = interrogater.nextLine();
    pipe.println("Entering Address.town as Location:");
    ParamsCollector town = getLocation();
    return new ParamsCollector(new ParamsCollector[]{town}, null, null, new String[]{zipCode});
  }
  private ParamsCollector getLocation() {
    pipe.print("Enter a long integer Location.x: ");
    long x = interrogater.nextLong();
    pipe.print("Enter a long integer Location.y: ");
    long y = interrogater.nextLong();
    pipe.print("Enter a double fraction Location.z: ");
    double z = interrogater.nextDouble();
    return new ParamsCollector(null, new long[]{x, y}, new double[]{z}, null);
  }
}