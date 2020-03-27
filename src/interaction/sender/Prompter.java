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
   * Для основного {@link Invoker}'а
   * @param pipeout поток вывода для общения с пользователем
   */
  public Prompter(PrintStream pipeout) {
    pipe = pipeout;
    dictionary = new HashMap<>();
  }
  /**
   * Дополнительный конструктор для {@link Invoker}'ов вызываемых из других {@link Invoker}'ов.
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
      case Add.NAME:
      case RemoveLower.NAME:
        Junker element = null;
        if (!(this instanceof FilePrompter)) while (element == null) element = getOrganization();
        else {
          element = getOrganization();
          if (element == null) {
            pipe.println("Произошли ошибки при чтении файла: за подробной информацией смотреть выше");
            return false;
          }
        }
        if (command_name.equals(Add.NAME)) ((Add)dictionary.get(command_name)).commit(element);
        if (command_name.equals(RemoveLower.NAME)) ((RemoveLower)dictionary.get(command_name)).commit(element);
        dictionary.get(command_name).execute();
      return true;
      default: pipe.println("Команды с названием " + command_name + " не найдено"); return false;
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
        Junker element = null;
        if (!(this instanceof FilePrompter))
          while (element == null) element = getOrganization();
        else {
          element = getOrganization();
          if (element == null) {
            pipe.println("Произошли ошибки при чтении файла: за подробной информацией смотреть выше");
            return false;
          }
        }
        Insert command = (Insert) dictionary.get(command_name);
        command.commit(key, element);
        command.execute();
      }
      return true;
      case Update.NAME: {
        int id = Integer.valueOf(argument);
        Junker element = null;
        Update command = (Update) dictionary.get(command_name);
        if (command.commit(id, element)) {
          if (!(this instanceof FilePrompter))
            while (element == null) element = getOrganization();
          else {
            element = getOrganization();
            if (element == null) {
              pipe.println("Произошли ошибки при чтении файла: за подробной информацией смотреть выше");
              return false;
            }
          }
          command.commit(id, element);
          command.execute();
        } else { pipe.println("No such id: " + id); return false; }
      }
      return true;
      case RemoveKey.NAME: {
        Integer key;
        try {
          key = Integer.valueOf(argument);
        } catch (NumberFormatException ex) {
          System.err.println("Error:Argument should be a number!");
          return false;
        }
        RemoveKey command = (RemoveKey) dictionary.get(command_name);
        command.openKey(key);
        command.execute();
      }
      return true;
      // обработка execute_script:
      case ExecuteScript.NAME: {
        String filename = argument;
        ExecuteScript command = (ExecuteScript) dictionary.get(ExecuteScript.NAME);
        String sep = System.getProperty("file.separator");
        try (InputStream istream = new FileInputStream("scripts" + sep + filename)) {
          boolean isCollission = false;
          Prompter pointer = this;
          while (pointer != null) {
            if (istream.equals(pointer.recited)) isCollission = true;
            pointer = pointer.node;
          }
          try {
            if (isCollission) throw new RecursionFoundException();
          } catch (RecursionFoundException e) {
            pipe.println("Обнаружена попытка создать рекурсию. Проверьте последовательность запуска файлов.");
            return true;
          }
        } catch (FileNotFoundException e) {
          pipe.println("Файл по заданному имени не найден. Проверьте лежит ли " + filename + "в папке scripts");
          return true;
        } catch (IOException e) {
          pipe.println("Произошла критическая ошибка в вашей файловой системе.");
          System.exit(0);
        }
        command.setFile(filename);
        command.setCommandList(dictionary);
        command.execute();
      }
      return true;
      case ReplaceIfLower.NAME: {
        Integer key = Integer.valueOf(argument);
        Junker element = null;
        if (!(this instanceof FilePrompter))
          while (element == null) element = getOrganization();
        else {
          element = getOrganization();
          if (element == null) {
            pipe.println("Произошли ошибки при чтении файла: за подробной информацией смотреть выше");
            return false;
          }
        }
        ReplaceIfLower command = (ReplaceIfLower) dictionary.get(command_name);
        if (command.openKey(key)) {
          while (element == null) element = getOrganization();
          command.openKey(key);
          command.commit(element);
          command.execute();
        } else {
          System.out.println("No such key: " + key);
        }
      }
      return true;
      case ReplaceIfGreater.NAME: {
        Integer key = Integer.valueOf(argument);
        Junker element = null;
        ReplaceIfGreater command = (ReplaceIfGreater) dictionary.get(command_name);
        if (command.openKey(key)) {
          if (!(this instanceof FilePrompter))
            while (element == null) element = getOrganization();
          else {
            element = getOrganization();
            if (element == null) {
              pipe.println("Произошли ошибки при чтении файла: за подробной информацией смотреть выше");
              return false;
            }
          }
          command.openKey(key);
          command.commit(element);
          command.execute();
        } else {
          System.out.println("No such key: " + key);
        }
      }
        return true;
        case FilterContainsName.NAME: {
          FilterContainsName command = (FilterContainsName) dictionary.get(command_name);
          command.searchFor(argument);
          command.execute();
        }
        return true;
        default: { pipe.println("Команда по имени: " + command_name + " не найдена"); return false; }
    }
  }

  /**
   * Геттер, выкидывающий ссылку на поток вывода
   * @return ссылка на поток вывода, с помощью которога уведомляется пользователь
   */
  @Override public PrintStream getMainStream() { return pipe;}

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
  public final class Junker {
    private final Junker[] internals;
    private final long[] integers;
    private final double[] fractions;
    private final String[] lines;

    /**
     * Развернутый конструктор для указания всех параметров
     * @param internals ParamsCollector[] ссылка на массив сборщика внутренних араметров
     * @param integers long[] массив значений целочисленных полей
     * @param fractions double[] массив значений действительных полей
     * @param lines String[] массив строковых данных
     */
    public Junker(Junker[] internals, long[] integers, double[] fractions, String[] lines) {
      this.internals = internals;
      this.integers = integers;
      this.fractions = fractions;
      this.lines = lines;
    }
    /**
     * Геттер для получения ссылки на массив ссылок на внутренние хранилища параметров
     * @return массив ссылок на экземпляры {@link Junker}, т.е. на свой же тип
     */
    public Junker[] getInternals() { return internals; }
    /**
     * Геттер для получения ссылки массива целочисленных полей
     * @return long[] массив целых значений поля объекта
     */
    public long[] getIntegers() { return integers; }
    /**
     * Геттер для получения ссылки на массив дробных полей
     * @return double[] массив дробных значений поля объекта
     */
    public double[] getFractions() { return fractions; }
    /**
     * Геттер для получения строковых значений полей
     * @return String[] массив полей-строк, а также объектов представимые в виде строк
     */
    public String[] getLines() { return lines; }
  }
  /**
   * Не геттер, а процедура, формирующая экземпляр сборщика параметров класса коллекции.
   * Посредством диалога с пользователем или файлом считывает нужные параметры класса
   * @return {@link Junker} класс, хранящий беспорядочные данные об объекте типа коллекции
   */
  protected abstract Junker getOrganization();
  /**
   * Не геттер, а процедура, формирующая экземпляр сборщика параметров класса, являющегося частью элемента коллекции.
   * Посредством диалога с пользователем или файлом считывает нужные параметры класса
   * @return {@link Junker} класс, хранящий беспорядочные данные об объекте типа, являющегося частью элемента коллекции
   */
  protected abstract Junker getCoordinates();
  /**
   * Не геттер, а процедура, формирующая экземпляр сборщика параметров класса, являющегося частью элемента коллекции.
   * Посредством диалога с пользователем или файлом считывает нужные параметры класса
   * @return {@link Junker} класс, хранящий беспорядочные данные об объекте типа, являющегося частью элемента коллекции
   */
  protected abstract Junker getAddress();
  /**
   * Не геттер, а процедура, формирующая экземпляр сборщика параметров класса, являющегося частью элемента коллекции.
   * Посредством диалога с пользователем или файлом считывает нужные параметры класса
   * @return {@link Junker} класс, хранящий беспорядочные данные об объекте типа, являющегося частью элемента коллекции
   */
  protected abstract Junker getLocation();
}