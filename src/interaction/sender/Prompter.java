package interaction.sender;

import exceptions.RecursionFoundException;
import interaction.instructions.Command;
import interaction.instructions.base.*;
import interaction.instructions.extended.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Абстрактный класс реализующий интерфейс Invoker, добавляет некоторые жизненно-необходимые поля
 * и реализацию общих методов, а также требований к наследникам.
 * Содержит также описание класса хранящего параметры для сбора объектов.
 * Хранит ссылку на Prompter из которого он был вызван или null, если такового не было - структура связного списка с ссылкой на предыдущий,
 * нужен лишь для отслеживания попыток повторного обращения к файлам или другим потокам ввода.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see interaction.sender.Invoker
 * @see ConsolePrompter
 * @see FilePrompter
 * @see interaction.Shell
 * @see Command
 * @see interaction.instructions.Decree
 * @see Committer
 * @see Recorder
 */
public abstract class Prompter implements Invoker {
  protected Prompter node;
  protected InputStream recited;
  protected final PrintStream pipe;
  protected final Map<String, Command> dictionary;

  /**
   *
   * @param pipeout
   */
  protected Prompter(PrintStream pipeout) {
    pipe = pipeout;
    dictionary = new HashMap<>();
    recited = null;
    node = null;
  }

  /**
   *
   * @param pipeout
   * @param upnode
   */
  protected Prompter(PrintStream pipeout, Prompter upnode) {
    this(pipeout);
    node = upnode;
  }

  /**
   *
   * @param command_name, Command instruct
   * @param instruct
   */
  @Override
  public void signup(String command_name, Command instruct) {
    dictionary.put(command_name, instruct);
  }

  /**
   *
   * @param command_name
   * @return
   */
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

  /**
   *
   * @param command_name
   * @param argument
   * @return
   * @throws NumberFormatException
   */
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

  /**
   *
   * @return
   */
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
  /**
   *
   * @return
   */
  protected abstract ParamsCollector getOrganization();
  /**
   *
   * @return
   */
  protected abstract ParamsCollector getCoordinates();
  /**
   *
   * @return
   */
  protected abstract ParamsCollector getAddress();
  /**
   *
   * @return
   */
  protected abstract ParamsCollector getLocation();
}