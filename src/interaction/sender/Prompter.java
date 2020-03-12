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
   * Стандартный конструктор класса, выставляющий все поля по умолчанию
   * Для основного Invoker'а
   * @param pipeout поток вывода для общения с пользователем
   */
  protected Prompter(PrintStream pipeout) {
    pipe = pipeout;
    dictionary = new HashMap<>();
  }

  /**
   * Дополнительный конструктор для Invoker'ов вызываемых из других Invoker'ов.
   * @param pipeout поток вывода для общения с пользователем
   * @param upnode ссылка на предыдущий Invoker, из которого произошел вызов
   */
  protected Prompter(PrintStream pipeout, Prompter upnode) {
    this(pipeout);
    node = upnode;
  }

  /**
   * Метод, добавляющий команду в соответсвующий список под определённым названием
   * @param command_name String строковое имя команды
   * @param instruct Command объект команды
   */
  @Override
  public void signup(String command_name, Command instruct) {
    dictionary.put(command_name, instruct);
  }

  /**
   * Вызов команды по ее названию, вызывает только команды
   * <strong>не требующие аргументов</strong>
   * @param command_name String название команды
   * @return boolean признак успеха <strong>вызова</strong> команды
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
   * Вызов команды по ее названию и
   * <strong>с соответсвующим аргументом</strong>
   * @param command_name название команды
   * @param argument агрумент команды
   * @return признак успешности вызова команды
   * @throws NumberFormatException исключение выкидываемое при ошибке привидения к числу
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
        try(FileInputStream input = new FileInputStream(System.getProperty("user.dir") + "/scripts/" + argument)) {
          Prompter pointer = node;
          boolean isCollission = false;
          while (pointer != null) {
            if (pointer.recited.equals(input))
              isCollission = true;
            pointer = pointer.node;
          }
          if (!isCollission) {
            filePrompter = new FilePrompter(System.err, input);
            for (Map.Entry<String, Command> enter: dictionary.entrySet()) filePrompter.dictionary.put(enter.getKey(), enter.getValue());
            while (filePrompter.scan());
          } else throw new RecursionFoundException();
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
   * Геттер, выкидывающий ссылку на поток вывода
   * @return ссылка на поток вывода, с помощью которога уведомляется пользователь
   */
  @Override public PrintStream getMainStream() {return pipe;}

  /**
   * Внутренний класс, инкапсулирующий параметры собираемых объектов, между
   * элементами паттерна.
   * <center><strong>DISCLAIMER</strong></center>
   * <hr>
   * <p><b>
   *   <em>Внимание!</em> Данное архитектурное решение было принято единолично, в спешке и цейтноте,
   *   возможно, его автор в это время находился в состоянии алкогольного или наркотического опьянения;
   *   также возможно, что этот "субъект", зовущий себя программистом, либо герой романа Достоевского - "Идиот", либо
   *   законченный лентяй (в последнем даже можно не сомневаться), так как не выделил данное, стыдно говорит, <em>творение</em> в отдельный класс.
   *   И потому такое решение не то что не претендует на звание лучшего, более того, автор этого 8-го чуда света готов лично
   *   забрать все награды на "World Stipidity Award", а также каждый раз, открывая эту лагучую IDE и набирая поток говна, называющийся кодом,
   *   надевать колпак с надписью "DUNCE".
   * </b></p>
   * <span>Именно по этим вышеупомянутым причинам у этого класса стоит модификатор <i>final</i></span>
   * @author Come_1LL_F00 aka Lenar Khannanov
   * @see interaction.customer.TotalCommander
   * @see Invoker
   */
  public final class ParamsCollector {
    private final ParamsCollector[] internals;
    private final long[] integers;
    private final double[] fractions;
    private final String[] lines;

    /**
     *
     * @param internals
     * @param integers
     * @param fractions
     * @param lines
     */
    ParamsCollector(ParamsCollector[] internals, long[] integers, double[] fractions, String[] lines) {
      this.internals = internals;
      this.integers = integers;
      this.fractions = fractions;
      this.lines = lines;
    }

    /**
     * Геттер для получения ссылки на массив ссылок на внутренние хранилища параметров
     * @return массив ссылок на экземпляры ParamsCollector, т.е. на свой же тип
     */
    public ParamsCollector[] getInternals() { return internals; }

    /**
     * Геттер для получения ссылки массива
     * @return
     */
    public long[] getIntegers() { return integers; }

    /**
     *
     * @return
     */
    public double[] getFractions() { return fractions; }

    /**
     *
     * @return
     */
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