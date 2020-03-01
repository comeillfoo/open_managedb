package interaction.sender;

import entity.OrganizationType;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public final class ConsolePrompter extends Prompter {
  private final Scanner interrogater;
  public ConsolePrompter(PrintStream pipeout, InputStream pipein) {
    super(pipeout);
    recited = pipein;
    interrogater = new Scanner(recited);
    interrogater.useLocale(Locale.UK);
  }
  // TODO: apply a NULL-safety code
  @Override protected ParamsCollector getOrganization() {
    String name = scanLine(false, "Enter string Organization.name: ");
    ParamsCollector coordinates = null;
    while (coordinates == null) {
      pipe.println("Entering Organization.coordinates as Coordinates:");
      coordinates = getCoordinates();
    }
    String fullname = scanLine(true, "Enter string Organization.fullname: ");
    float annualTurnover = 0f; int employeesCount = 0;
    try {
      annualTurnover = scanFraction((number) -> (number > 0), "Enter some float fraction Organization.annualTurnover: ").floatValue();
      employeesCount = scanInteger((number) -> (number > 0), "Enter an integer Organization.employeesCount: ").intValue();
    } catch (InputMismatchException e) {
      pipe.println("Entered invalid number format. Please repeat your enterings from the very beginning.");
      interrogater.nextLine();
      return null;
    }
    pipe.println("Elect OrganizationType.type: ");
    for (OrganizationType orgtype : OrganizationType.values()) {
      pipe.println("\t+ " + orgtype);
    }
    String enumName = interrogater.nextLine(); // TODO: possibly absence of enumeration constant
    ParamsCollector officialAddress = null;
    while (officialAddress == null) {
      pipe.println("Entering Organization.officialAddress as Address:");
      officialAddress = getAddress();
    }
    return new ParamsCollector(
        new ParamsCollector[]{coordinates, officialAddress},
        new long[]{(long) employeesCount},
        new double[]{(double) annualTurnover},
        new String[]{name, fullname, enumName});
  }
  @Override protected ParamsCollector getCoordinates() {
    int x = 0; Float y = 0f;
    try {
      x = scanInteger((number) -> (true), "Enter an integer Coordinates.x: ").intValue();
      y = scanFraction((number) -> (number > -538), "Enter a float Coordinates.y: ").floatValue();
    } catch (InputMismatchException e) {
      pipe.println("Entered invalid number format. Please repeat your enterings from the very beginning.");
      interrogater.nextLine();
      return null;
    }
    return new ParamsCollector(null, new long[]{(long) x}, new double[]{(double) y}, null);
  }
  @Override protected ParamsCollector getAddress() {
    String zipCode = scanLine(true, "Enter string Address.zipCode: ");
    ParamsCollector town = null;
    while (town == null) {
      pipe.println("Entering Address.town as Location:");
      town = getLocation();
    }
    return new ParamsCollector(new ParamsCollector[]{town}, null, null, new String[]{zipCode});
  }
  @Override protected ParamsCollector getLocation() {
    long x = 0, y = 0; double z = 0;
    try {
      x = scanInteger((number) -> (true), "Enter a long integer Location.x: ");
      y = scanInteger((number) -> (true), "Enter a long integer Location.y: ");
      z = scanFraction((number) -> (true), "Enter a double fraction Location.z: ");
    } catch (InputMismatchException e) {
      pipe.println("Entered invalid number format. Please repeat your enterings from the very beginnning.");
      interrogater.nextLine();
      return null;
    }
    return new ParamsCollector(null, new long[]{x, y}, new double[]{z},null);
  }
  private Long scanInteger(NumChecker<Long> requests, String message) throws InputMismatchException {
    Long number = null;
    do {
      pipe.print(message);
      number = interrogater.nextLong();
      interrogater.nextLine();
    } while (number == null || !requests.check(number));
    return number;
  }
  private Double scanFraction(NumChecker<Double> requests, String message) throws InputMismatchException {
    Double number = null;
    do {
      pipe.print(message);
      number = interrogater.nextDouble();
      interrogater.nextLine();
    } while (number == null || !requests.check(number));
    return number;
  }
  private String scanLine(boolean mayBeEmpty, String message) {
    String line = null;
    do {
      pipe.print(message);
      line = interrogater.nextLine();
    } while (line == null || (line.isEmpty() && !mayBeEmpty));
    return line;
  }
  @Override public boolean scan() {
    String reply = "";
    while (reply.equals("")) {
      pipe.print("> ");
      reply = interrogater.nextLine();
    }
    String[] parsyee = reply.split(" ");
    if (parsyee == null || parsyee.length == 0 || !dictionary.containsKey(parsyee[0]))
      return true;
    try {
      if (parsyee.length > 1)
        return invoke(parsyee[0], parsyee[1]);
      else return invoke(parsyee[0]);
    } catch (NumberFormatException e) {
      pipe.println("Invalid command argument format:");
      pipe.println("Notice, please: " + dictionary.get(parsyee[0]));
      pipe.println(e.getMessage());
      pipe.println("For more information type \"help\" and try again...");
    }
    return false;
  }
}