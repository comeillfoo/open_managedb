package interaction.sender;

import entity.OrganizationType;
import exceptions.InvalidClassNameException;
import interaction.instructions.base.Insert;
import interaction.instructions.base.RemoveKey;
import interaction.instructions.base.Update;
import interaction.instructions.extended.ExecuteScript;
import interaction.instructions.extended.FilterContains;
import interaction.instructions.extended.ReplaceIf;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public final class ConsolePrompter extends Prompter {
  protected final Scanner interrogater;
  public ConsolePrompter(PrintStream pipeout, InputStream pipein) {
    super(pipeout);
    interrogater = new Scanner(pipein);
  }
  @Override protected ParamsCollector getParams(String className) {
    switch (className) {
      case "Organization": return getOrganization();
      case "Coordinates": return getCoordinates();
      case "Address": return getAddress();
      case "Location": return getLocation();
      default: pipe.println("There is no class with name like this."); return null;
    }
  }
  // TODO: apply a NULL-safety code
  @Override protected ParamsCollector getOrganization() {
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
    for (OrganizationType orgtype : OrganizationType.values()) {
      pipe.println("\t+ " + orgtype);
    }
    String enumName = interrogater.nextLine(); // TODO: possibly absence of enumeration constant
    pipe.println("Entering Organization.officialAddress as Address:");
    ParamsCollector officialAddress = getAddress();
    return new ParamsCollector(
        new ParamsCollector[]{coordinates, officialAddress},
        new long[]{(long) employeesCount},
        new double[]{(double) annualTurnover},
        new String[]{name, fullname, enumName});
  }
  @Override protected ParamsCollector getCoordinates() {
    pipe.print("Enter an integer Coordinates.x: ");
    int x = interrogater.nextInt();
    pipe.print("Enter a float Coordinates.y: ");
    Float y = interrogater.nextFloat();
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
    pipe.print("Enter a long integer Location.y: ");
    long y = interrogater.nextLong();
    pipe.print("Enter a double fraction Location.z: ");
    double z = interrogater.nextDouble();
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
  @Override public void invoke(String command_name) {

  }
  public void invoke(String command_name, String argument) {
    switch (command_name) {
      case Insert.NAME:{
        Integer key = Integer.valueOf(argument);
        ParamsCollector collection = getParams("Organization");
        Insert command = (Insert) dictionary.get(command_name);
        command.commit(key, collection);
        command.Execute();
      };
      break;
      case Update.NAME:{
        int id = Integer.valueOf(argument);
        ParamsCollector collection = getParams("Organization");
        Update command = (Update) dictionary.get(command_name);
        command.commit(id, collection);
        command.Execute();
      };
      break;
      case RemoveKey.NAME:{
        Integer key = Integer.valueOf(argument);
        RemoveKey command = (RemoveKey) dictionary.get(command_name);
        command.openKey(key);
        command.Execute();
      };
      break;
      case ExecuteScript.NAME:{
        FilePrompter filePrompter = null;
        try(FileInputStream input = new FileInputStream(argument)) {
          filePrompter = new FilePrompter(System.err, input);
        } catch (IOException e) {
          filePrompter.pipe.println(e.getMessage());
          filePrompter.pipe.println(e.getStackTrace());
        }
      };
      break;
      case ReplaceIf.NAME:
      // TODO: make an implementation
      break;
      case FilterContains.NAME:
      // TODO: make an implementation
      break;
      default: pipe.println("There is no command with such number of arguments...");
      break;
    }
  }
}