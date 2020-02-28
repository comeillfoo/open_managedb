package interaction.sender;

import entity.OrganizationType;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public final class ConsolePrompter extends Prompter {
  protected final Scanner interrogater;
  public ConsolePrompter(PrintStream pipeout, InputStream pipein) {
    super(pipeout);
    interrogater = new Scanner(pipein);
    interrogater.useLocale(Locale.UK);
  }
  // TODO: apply a NULL-safety code
  @Override protected ParamsCollector getOrganization() throws InputMismatchException {
    String name = "";
    ParamsCollector coordinates = null;
    float annualTurnover = 0;
    String fullName = "";
    int employeesCount = 0;
    String tempEnumName = "";
    String enumName = "";
    ParamsCollector officialAddress = null;
    while (true) {
      try {
        pipe.print("Enter string Organization.name: ");
        name = interrogater.nextLine();
        pipe.print("Enter string Organization.fullname: ");
        fullName = interrogater.nextLine();

        pipe.println("Elect OrganizationType.type: ");
        for (OrganizationType orgtype : OrganizationType.values()) {
          pipe.println("\t+ " + orgtype);
        }
        while (true) {
          tempEnumName = interrogater.nextLine();
          switch (tempEnumName.toLowerCase()){
            case "public":
            case "trust":
            case "private_limited_company":
            case "open_joint_stock_company":
              enumName = tempEnumName.toUpperCase();
              break;
            default:
              System.err.println("Error:Illegal company type!");
              pipe.println("Elect OrganizationType.type: ");
              for (OrganizationType orgtype : OrganizationType.values()) {
                pipe.println("\t+ " + orgtype);
              }
              continue;
          }
          break;
        }
        pipe.print("Enter an integer Organization.employeesCount: ");
        employeesCount = interrogater.nextInt();
        interrogater.nextLine();
        pipe.print("Enter some float fraction Organization.annualTurnover: ");
        annualTurnover = (float) interrogater.nextDouble();
        interrogater.nextLine();
        pipe.println("Entering Organization.coordinates as Coordinates:");
        coordinates = getCoordinates();
        pipe.println("Entering Organization.officialAddress as Address:");
        officialAddress = getAddress();
        break;
      } catch (InputMismatchException e) {
        interrogater.nextLine();
        System.err.println("Error: Incorrect data format!");
        System.out.println();
      }
    }
      return new ParamsCollector(
              new ParamsCollector[]{coordinates, officialAddress},
              new long[]{(long) employeesCount},
              new double[]{(double) annualTurnover},
              new String[]{name, fullName, enumName});
  }
  @Override protected ParamsCollector getCoordinates() {
    pipe.print("Enter an integer Coordinates.x: ");
    int x = interrogater.nextInt();
    interrogater.nextLine();
    pipe.print("Enter a float Coordinates.y: ");
    Float y = (float) interrogater.nextDouble();
    interrogater.nextLine();
    return new ParamsCollector(null, new long[]{(long) x}, new double[]{(double) y}, null);
  }
  @Override protected ParamsCollector getAddress() {
    pipe.print("Enter string Address.zipCode: ");
    String zipCode = interrogater.nextLine();
    pipe.println("Entering Address.town as Location:");
    ParamsCollector town = getLocation();
    return new ParamsCollector(new ParamsCollector[]{town}, null, null, new String[]{zipCode});
  }
  @Override protected ParamsCollector getLocation() {
    pipe.print("Enter a long integer Location.x: ");
    long x = interrogater.nextLong();
    interrogater.nextLine();
    pipe.print("Enter a long integer Location.y: ");
    long y = interrogater.nextLong();
    interrogater.nextLine();
    pipe.print("Enter a double fraction Location.z: ");
    double z = interrogater.nextDouble();
    interrogater.nextLine();
    return new ParamsCollector(null, new long[]{x, y}, new double[]{z},null);
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
    if (parsyee.length > 1)
      invoke(parsyee[0], parsyee[1]);
    else invoke(parsyee[0]);
    return false;
  }
}