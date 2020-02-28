package interaction.sender;

import interaction.instructions.Command;
import interaction.instructions.base.*;
import interaction.instructions.extended.*;

import java.io.FileInputStream;
import java.io.IOException;
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

  public Scanner getInterrogater() {
    return this.interrogater;
  }

  @Override
  public void signup(String command_name, Command instruct) {

    dictionary.put(command_name, instruct);
  }
  protected void invoke(String command_name) {
    switch (command_name) {
      case Help.NAME: dictionary.get(command_name).execute(); break;
      case Info.NAME: dictionary.get(command_name).execute(); break;
      case Show.NAME: dictionary.get(command_name).execute(); break;
      case Clear.NAME: dictionary.get(command_name).execute(); break;
      case Save.NAME: dictionary.get(command_name).execute(); break;
      case Exit.NAME: dictionary.get(command_name).execute(); break;
      default: pipe.println("There is no such command yet...");
    }
  }
  protected void invoke(String command_name, String argument) {
    switch (command_name) {
      case Insert.NAME:{
        Integer key = Integer.valueOf(argument);
        ParamsCollector collection = getOrganization();
        Insert command = (Insert) dictionary.get(command_name);
        command.commit(key, collection);
        command.execute();
      };
      break;
      case Update.NAME:{
        int id = Integer.valueOf(argument);
        ParamsCollector collection = getOrganization();
        Update command = (Update) dictionary.get(command_name);
        command.commit(id, collection);
        command.execute();
      };
      break;
      case RemoveKey.NAME:{
        Integer key = Integer.valueOf(argument);
        RemoveKey command = (RemoveKey) dictionary.get(command_name);
        command.openKey(key);
        command.execute();
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
      case ReplaceIfLower.NAME:
        // TODO: make an implementation
        break;
      case ReplaceIfGreater.NAME:
        // TODO: make an implementation
        break;
      case FilterContainsName.NAME:
        // TODO: make an implementation
        break;
      default: pipe.println("There is no command with such number of arguments...");
        break;
    }
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
  //TODO: check if some errors cast
  protected abstract ParamsCollector getOrganization();
  protected abstract ParamsCollector getCoordinates();
  protected abstract ParamsCollector getAddress();
  protected abstract ParamsCollector getLocation();
}