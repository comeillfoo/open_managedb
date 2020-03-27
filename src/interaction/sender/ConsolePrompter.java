package interaction.sender;

import entity.OrganizationType;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Класс -- реализация интерфейса {@link Invoker}, организующий взаимодействие с пользователем
 * и считывающий, введенные им команды и их параметры.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Invoker
 * @see Prompter
 * @see FilePrompter
 * @see interaction.sender.Prompter.Junker
 */
public final class ConsolePrompter extends Prompter {
  private final Scanner interrogater;
  public ConsolePrompter(PrintStream pipeout, InputStream pipein) {
    super(pipeout);
    recited = pipein;
    interrogater = new Scanner(recited);
    interrogater.useLocale(Locale.UK);
  }
  // TODO: apply a NULL-safety code

  /**
   * Метод, считывающий параметры от пользователя, для формирования сборщика параметров класса
   * @return {@link interaction.sender.Prompter.Junker} сборщик параметров
   */
  @Override protected Junker getOrganization() {
    String name = scanLine(false, "Enter string Organization.name: ");
    Junker coordinates = null;
    while (coordinates == null) {
        pipe.println("Entering Organization.coordinates as Coordinates:");
        coordinates = getCoordinates();
    }
    String fullname = scanLine(true, "Enter string Organization.fullname: ");
    float annualTurnover = 0f; int employeesCount = 0;
    try {
      annualTurnover = scanFraction((number) -> (number > 0), "Enter some float fraction Organization.annualTurnover: ").floatValue();
      employeesCount = scanInteger((number) -> (number > 0), "Enter an integer Organization.employeesCount: ").intValue();
    } catch (InputMismatchException | NumberFormatException e) {
      pipe.println("Entered invalid number format. Please repeat your enterings from the very beginning.");
      return null;
    }
    pipe.println("Elect OrganizationType.type: ");
    for (OrganizationType orgtype : OrganizationType.values()) {
      pipe.println("\t+ " + orgtype);
    }
    String enumName = ""; boolean condition = false;
    ENUMENTER:
    {
      condition = !condition;
      while (condition) {
        enumName = interrogater.nextLine(); // TODO: possibly absence of enumeration constant
        for (OrganizationType orgtype : OrganizationType.values())
          if (orgtype.toString().toUpperCase().startsWith(enumName.toUpperCase())) {
            enumName = orgtype.toString();
            break ENUMENTER;
          }
      }
    }
    Junker officialAddress = null;
    while (officialAddress == null) {
      pipe.println("Entering Organization.officialAddress as Address:");
      officialAddress = getAddress();
    }
    return new Junker(
        new Junker[]{coordinates, officialAddress},
        new long[]{(long) employeesCount},
        new double[]{(double) annualTurnover},
        new String[]{name, fullname, enumName});
  }

  /**
   * Метод, считывающий параметры от пользователя, для формирования сборщика параметров класса
   * @return {@link interaction.sender.Prompter.Junker} сборщик параметров
   */
  @Override protected Junker getCoordinates() {
    int x = 0; Float y = 0f;
    try {
      x = scanInteger((number) -> (true), "Enter an integer Coordinates.x: ").intValue();
      y = scanFraction((number) -> (number > -538), "Enter a float Coordinates.y: ").floatValue();
    } catch (InputMismatchException | NumberFormatException e) {
      pipe.println("Entered invalid number format. Please repeat your enterings from the very beginning.");
      return null;
    }
    return new Junker(null, new long[]{(long) x}, new double[]{(double) y}, null);
  }

  /**
   * Метод, считывающий параметры от пользователя, для формирования сборщика параметров класса
   * @return {@link interaction.sender.Prompter.Junker} сборщик параметров
   */
  @Override protected Junker getAddress() {
    String zipCode = scanLine(true, "Enter string Address.zipCode: ");
    Junker town = null;
    while (town == null) {
      pipe.println("Entering Address.town as Location:");
      town = getLocation();
    }
    return new Junker(new Junker[]{town}, null, null, new String[]{zipCode});
  }

  /**
   * Метод, считывающий параметры от пользователя, для формирования сборщика параметров класса
   * @return {@link interaction.sender.Prompter.Junker} сборщик параметров
   */
  @Override protected Junker getLocation() {
    long x = 0, y = 0; double z = 0;
    try {
      x = scanInteger((number) -> (true), "Enter a long integer Location.x: ");
      y = scanInteger((number) -> (true), "Enter a long integer Location.y: ");
      z = scanFraction((number) -> (true), "Enter a double fraction Location.z: ");
    } catch (InputMismatchException | NumberFormatException e) {
      pipe.println("Entered invalid number format. Please repeat your enterings from the very beginnning.");
      return null;
    }
    return new Junker(null, new long[]{x, y}, new double[]{z},null);
  }
  /**
   * Вспомогательный метод считывания целочисленного значения от пользователя
   * @param requests реализация интерфейса, проверяющего число на соответсвие условию
   * @param message String сообщение, выводящееся для взаимодействия с пользователем
   * @return считанное целочисленное значение
   * @throws InputMismatchException исключение об ошибке введенного типа
   * @throws NumberFormatException исключение об ошибочном формата числа
   */
  private Long scanInteger(NumChecker<Long> requests, String message) throws InputMismatchException, NumberFormatException {
    Long number = null;
    do {
      pipe.print(message);
      number = Long.valueOf(interrogater.nextLine());
    } while (number == null || !requests.check(number));
    return number;
  }

  /**
   * Вспомогательный метод для считывания дробных значений основных базовых типов от пользователя
   * @param requests реализация интерфейса, проверяющего число на соответсвие условию
   * @param message String сообщение, выводящееся для взаимодействия с пользователем
   * @return Double считанное действительное число, удовлетворяющее заданным условиям
   * @throws InputMismatchException исключение об ошибке введенного типа
   * @throws NumberFormatException исключение об ошибочном формата числа
   */
  private Double scanFraction(NumChecker<Double> requests, String message) throws InputMismatchException, NumberFormatException {
    Double number = null;
    do {
      pipe.print(message);
      number = Double.valueOf(interrogater.nextLine());
    } while (number == null || !requests.check(number));
    return number;
  }

  /**
   * Вспомогательный метод, считывания строковых значений
   * @param mayBeEmpty булевый признак, означающий возможность строки быть пустой
   * @param message String сообщение, выводящееся для взаимодействия с пользователем
   * @return String считанная строка, удовлетворяющая заданным условиям
   */
  private String scanLine(boolean mayBeEmpty, String message) {
    String line = null;
    do {
      pipe.print(message);
      line = interrogater.nextLine();
    } while (line == null || (line.isEmpty() && !mayBeEmpty));
    return line;
  }

  /**
   * Метод, считывающий команды и их параметры от пользователя, до окончания работы с коллекцией,
   * т.е. ввода команды {@link interaction.instructions.base.Exit}, закрытия консоли/терминалов или (что более вероятно, чем первые два случаи)
   * возкнивения ошибок и вброс исключений.
   * @return признак успешности вызова комманд в виде булевой константы
   */
  @Override public boolean scan() {
    String reply = "";
    while (reply.equals("")) {
      pipe.print("> ");
      reply = interrogater.nextLine();
    }
    String[] parsyee = reply.split(" ");
    if (parsyee == null || parsyee.length == 0 || !dictionary.containsKey(parsyee[0]))
      return true;
    try {
      if (parsyee.length > 1)
        return invoke(parsyee[0], parsyee[1]);
      else return invoke(parsyee[0]);
    } catch (NumberFormatException e) {
      pipe.println("Invalid command argument format:");
      pipe.println("Notice, please: " + dictionary.get(parsyee[0]));
      pipe.println(e.getMessage());
      pipe.println("For more information type \"help\" and try again...");
    }
    return false;
  }
}