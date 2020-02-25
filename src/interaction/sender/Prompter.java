package interaction.sender;

import exceptions.InvalidClassNameException;
import interaction.instructions.Command;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public abstract class Prompter implements Invoker {
  protected final PrintStream pipe;
  protected final Map<String, Command> dictionary;
  public Prompter(PrintStream pipeout) {
    pipe = pipeout;
    dictionary = new HashMap<>();
  }
  @Override public void signup(String command_name, Command instruct) {
    dictionary.put(command_name, instruct);
  }
  @Override public PrintStream getMainStream() {return pipe;}
  public final class ParamsCollector {
    private final ParamsCollector[] internals;
    private final long[] integers;
    private final double[] fractions;
    private final String[] lines;
    ParamsCollector(ParamsCollector[] internals, long[] integers, double[] fractions, String[] lines) {
      this.internals = internals;
      this.integers = integers;
      this.fractions = fractions;
      this.lines = lines;
    }
    public ParamsCollector[] getInternals() { return internals; }
    public long[] getIntegers() { return integers; }
    public double[] getFractions() { return fractions; }
    public String[] getLines() { return lines; }
  }
  protected abstract ParamsCollector getParams(String className) throws InvalidClassNameException;
  protected abstract ParamsCollector getOrganization();
  protected abstract ParamsCollector getCoordinates();
  protected abstract ParamsCollector getAddress();
  protected abstract ParamsCollector getLocation();
}