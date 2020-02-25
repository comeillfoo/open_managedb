package interaction.sender;

import exceptions.InvalidClassNameException;

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
  @Override public ParamsCollector getParams(String className) {
    return null;
  }
  @Override protected ParamsCollector getOrganization() {
    return null;
  }
  @Override protected ParamsCollector getCoordinates() {
    return null;
  }
  @Override protected ParamsCollector getAddress() {
    return null;
  }
  @Override protected ParamsCollector getLocation() {
    return null;
  }
  @Override public boolean scan() {
    return false;
  }
  @Override public void invoke(String command_name) {

  }
}
