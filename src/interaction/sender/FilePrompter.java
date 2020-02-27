package interaction.sender;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public final class FilePrompter extends Prompter {
  private final InputStreamReader reader;
  public FilePrompter(PrintStream pipeout, InputStream pipein) {
    super(pipeout);
    reader = new InputStreamReader(pipein);
  }
  // TODO: apply a NULL-safety code and reading from file
  private String readline() throws IOException {
    StringBuilder line = new StringBuilder("");
    if (reader.ready()) {
      char c;
      while ((c = (char) reader.read()) != '\n') line.append(c);
      return line.toString();
    } else return "file end...";
  }
  private String getArg() throws IOException {
    String reply = readline();
    String[] parsyee = reply.split("^(.*)?: (.*)?$");
    if (parsyee != null)
      return new String(parsyee[1]);
    else return "";
  }
  @Override protected ParamsCollector getOrganization() {
    try {
      String name = getArg();
      ParamsCollector coordinates = getCoordinates();
      float annualTurnover = Float.valueOf(getArg());
      String fullname = getArg();
      int employeesCount = Integer.valueOf(getArg());
      String enumName = getArg();
      ParamsCollector officialAddress = getAddress();
      return new ParamsCollector(
          new ParamsCollector[]{coordinates, officialAddress},
          new long[]{ employeesCount},
          new double[]{ annualTurnover},
          new String[]{name, fullname, enumName});
    } catch (IOException e) {
      pipe.println(e.getMessage());
      pipe.println(e.getStackTrace());
    }
    return null;
  }
  @Override protected ParamsCollector getCoordinates() {
    try {
      int x = Integer.valueOf(getArg());
      float y = Float.valueOf(getArg());
      return new ParamsCollector(null, new long[] {x}, new double[]{y}, null);
    } catch (IOException e) {
      pipe.println(e.getMessage());
      pipe.println(e.getStackTrace());
    }
    return null;
  }
  @Override protected ParamsCollector getAddress() {
    try {
      String zipCode = getArg();
      ParamsCollector town = getLocation();
      return new ParamsCollector(new ParamsCollector[]{town}, null, null, new String[]{zipCode});
    } catch (IOException e) {
      pipe.println(e.getMessage());
      pipe.println(e.getStackTrace());
    }
    return null;
  }
  @Override protected ParamsCollector getLocation() {
    try {
      long x = Long.valueOf(getArg());
      long y = Long.valueOf(getArg());
      double z = Double.valueOf(getArg());
      return new ParamsCollector(null, new long[]{x, y}, new double[]{z}, null);
    } catch (IOException e) {
      pipe.println(e.getMessage());
      pipe.println(e.getStackTrace());
    }
    return null;
  }
  @Override public boolean scan() {
    try {
      while (reader.ready()) {
        String line = readline();
        String[] parsyee = line.split(" ");
        if (parsyee == null || parsyee.length == 0 || !dictionary.containsKey(parsyee[0]))
          return true;
        if (parsyee.length > 1)
          invoke(parsyee[0], parsyee[1]);
        else invoke(parsyee[0]);
        return false;
      }
    } catch (IOException e) {
      pipe.println(e.getMessage());
      pipe.println(e.getStackTrace());
    }
    return true;
  }
}
