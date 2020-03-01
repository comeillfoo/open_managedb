package interaction.sender;

import exceptions.RecursionFoundException;
import interaction.instructions.Command;
import interaction.instructions.base.*;
import interaction.instructions.extended.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public abstract class Prompter implements Invoker {
  protected Prompter node;
  protected InputStream recited;
  protected final PrintStream pipe;
  protected final Map<String, Command> dictionary;
  protected Prompter(PrintStream pipeout) {
    pipe = pipeout;
    dictionary = new HashMap<>();
    recited = null;
    node = null;
  }
  protected Prompter(PrintStream pipeout, Prompter upnode) {
    this(pipeout);
    node = upnode;
  }

  @Override
  public void signup(String command_name, Command instruct) {

    dictionary.put(command_name, instruct);
  }
  protected boolean invoke(String command_name) {
    switch (command_name) {
      case Help.NAME:
      case Info.NAME:
      case Show.NAME:
      case Clear.NAME:
      case Save.NAME:
      case Exit.NAME:
      case SumOfAnnualTurnover.NAME:
      case MaxByDate.NAME: dictionary.get(command_name).execute(); return true;
      case RemoveLower.NAME: {
        ParamsCollector element = null;
        while (element == null) element = getOrganization();
        dictionary.get(command_name).execute();}
      return true;
      default: pipe.println("There is no such command yet...");
      return false;
    }
  }
  protected boolean invoke(String command_name, String argument) throws NumberFormatException {
    switch (command_name) {
      case Insert.NAME: {
        Integer key = Integer.valueOf(argument);
        ParamsCollector element = null;
        while (element == null) element = getOrganization();
        Insert command = (Insert) dictionary.get(command_name);
        command.commit(key, element);
        command.execute();}
      return true;
      case Update.NAME:{
        int id = Integer.valueOf(argument);
        ParamsCollector element = null;
        while (element == null) element = getOrganization();
        Update command = (Update) dictionary.get(command_name);
        command.commit(id, element);
        command.execute();}
      return true;
      case RemoveKey.NAME:{
        Integer key = Integer.valueOf(argument);
        RemoveKey command = (RemoveKey) dictionary.get(command_name);
        command.openKey(key);
        command.execute();}
      return true;
      case ExecuteScript.NAME:{
        FilePrompter filePrompter = null;
        try(FileInputStream input = new FileInputStream(argument)) {
          Prompter pointer = node;
          boolean isCollission = false;
          while (pointer != null) {
            if (pointer.recited.equals(input))
              isCollission = true;
            pointer = pointer.node;
          }
          if (!isCollission)
            filePrompter = new FilePrompter(System.err, input);
          else throw new RecursionFoundException();
        } catch (IOException e) {
          filePrompter.pipe.println(e.getMessage());
          filePrompter.pipe.println(e.getStackTrace());
        } catch (RecursionFoundException e) {
        }
      }
      return true;
      case ReplaceIfLower.NAME: {
        Integer key = Integer.valueOf(argument);
        ParamsCollector element = null;
        while (element == null) element = getOrganization();
        ReplaceIfLower command = (ReplaceIfLower) dictionary.get(command_name);
        command.openKey(key);
        command.commit(element);
        command.execute();}
      return true;
      case ReplaceIfGreater.NAME: {
        Integer key = Integer.valueOf(argument);
        ParamsCollector element = null;
        while (element == null) element = getOrganization();
        ReplaceIfGreater command = (ReplaceIfGreater) dictionary.get(command_name);
        command.openKey(key);
        command.commit(element);}
      return true;
      case FilterContainsName.NAME: {
        FilterContainsName command = (FilterContainsName) dictionary.get(command_name);
        command.searchFor(argument);
        command.execute();}
      return true;
      default: pipe.println("There is no command with such number of arguments...");
      return false;
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