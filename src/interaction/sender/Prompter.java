package interaction.sender;

import exceptions.InvalidClassNameException;
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
  @Override public void signup(String command_name, Command instruct) {
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
      case Add.NAME:{
        ParamsCollector collector = getOrganization();
        Add command = (Add) dictionary.get(command_name);
        command.commit(collector);
        command.execute();
        break;
      }
      default: pipe.println("Something wrong with your syntax or command \"" + command_name + "\" not found!\n" +
              "Use \"help\" to find more information.");
    }
  }
  protected void invoke(String command_name, String argument) throws NumberFormatException {
    Integer key = null;
    int id = 0;
    switch (command_name) {
      case Insert.NAME:{
        try {
          key = Integer.valueOf(argument);
        }catch (NumberFormatException ex){
          System.err.println("Error:Argument should be a number!");
          ex.printStackTrace();
          break;
        }
        ParamsCollector collection = getOrganization();
        Insert command = (Insert) dictionary.get(command_name);
        command.commit(key, collection);
        command.execute();
      };
      break;
      case Update.NAME:{
        try {
          id = Integer.valueOf(argument);
        }catch (NumberFormatException ex){
          System.err.println("Error:Argument should be a number!");
        }
        ParamsCollector collection = getOrganization();
        Update command = (Update) dictionary.get(command_name);
        if(command.commit(id, collection)){
          command.execute();
        }else {
          System.err.println("Error:There is no such \"id\"!");
        }
      };
      break;
      case RemoveKey.NAME:{
        try {
          key = Integer.valueOf(argument);
        }catch (NumberFormatException ex){
          System.err.println("Error:Argument should be a number!");
          break;
        }
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