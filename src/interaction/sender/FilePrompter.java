package interaction.sender;

import entity.OrganizationType;
import exceptions.FileInvalidSyntaxException;

import java.io.*;

/**
 * Реализация интерфейса {@link Invoker} для обработки файлов со скриптами.
 * Для обработки каждого файла скрипта создается отдельный экземпляр интерпретатора,
 * анализирующего файл построчно. Файл обязан следовать определенному синтаксису.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see NumChecker
 * @see Invoker
 * @see Prompter
 * @see ConsolePrompter
 */
public final class FilePrompter extends Prompter {
  private final BufferedReader reader;

  /**
   * Развернутый конструктор для указания всех, используемых параметров.
   * Устанавливает поток ввода и вывода для чтения и оповещения об ошибках.
   * @param pipeout поток вывода
   * @param pipein поток ввода
   */
  public FilePrompter(PrintStream pipeout, InputStream pipein) {
    super(pipeout);
    recited = pipein;
    reader = new BufferedReader(new InputStreamReader(pipein));
  }
  // TODO: apply a NULL-safety code and reading from file

  /**
   * Переопределенный метод, возвращающей сборку из параметров класса элемента коллекции
   * @return {@link interaction.sender.Prompter.Junker} параметры класса
   */
  @Override public Junker getOrganization() {
    String name, fullname, type;
    double annualTurnover; int employeesCount;
    try {
      name = scanLine("Organization.name:");
      if (name.isEmpty()) throw new FileInvalidSyntaxException("Ошибка разбора файла: обнаружено пустое значение");
      Junker coordinates = getCoordinates();
      if (coordinates == null) return null;
      fullname = scanLine("Organization.fullname:");
      annualTurnover = Double.valueOf(scanLine("Organization.annualTurnover:"));
      if (annualTurnover <= 0) throw new FileInvalidSyntaxException("Ошибка чтение файла: поле annualTurnover должно быть больше 0.");
      employeesCount = Integer.valueOf(scanLine("Organization.employeesCount:"));
      if (employeesCount <= 0) throw new FileInvalidSyntaxException("Ошибка чтения файла: поле employeesCount должно быть больше 0");
      type = scanLine("Organization.type:");
      boolean found = false;
      if (type != null) {
        for (OrganizationType t : OrganizationType.values())
          if (t.toString().startsWith(type.toUpperCase())) { type = t.toString(); found = true; }
      }
      if (!found) type = "PUBLIC";
      Junker address = getAddress();
      if (address == null) pipe.println("Возникли ошибки при формировании Address. Аргумент будет расценен как null.");
      return new Junker(new Junker[]{coordinates, address}, new long[]{employeesCount}, new double[]{annualTurnover}, new String[]{name, fullname, type});
    } catch (FileInvalidSyntaxException e) {
      pipe.println(e.getMessage());
      return null;
    } catch (IOException e) {
      pipe.println("Произошла критическая ошибка ФС при чтении файла.");
      System.exit(0);
    } catch (NumberFormatException e) {
      pipe.println("Ошибка разбора файла: ожидался другой тип");
      return null;
    }
    return null;
  }
  private String scanLine(String parameter) throws IOException, FileInvalidSyntaxException {
    String line;
    if (reader.ready() && (line = reader.readLine()) != null) {
      pipe.println("Строка файла разобрана: " + (line == null? "" : line));
      String[] parsed = line.split(" ");
      if (parsed != null || parsed.length >= 2) {
        if (parameter.startsWith(parsed[0])) return parsed[1];
        else throw new FileInvalidSyntaxException("Ошибка разбора файла: ожидался ввод параметра: " + parameter);
      } else throw new FileInvalidSyntaxException("Ошибка разбора файла: предоставлены не все аргументы, либо аргументы имеют слишком большую длину");
    } else throw new FileInvalidSyntaxException("Ошибка разбора файла, встречен его неожиданный конец");
  }
  /**
   * Переопределенный метод, возвращающей сборку из параметров класса, являющегося частью элемента коллекции
   * @return {@link interaction.sender.Prompter.Junker} параметры класса
   */
  @Override public Junker getCoordinates() {
    long x = 0; float y = 0.0f;
    try {
      x = Long.valueOf(scanLine("Coordinates.x:"));
      y = Float.valueOf(scanLine("Coordinates.y:"));
      if (y <= -538) throw new FileInvalidSyntaxException("Ошибка разбора файла: поле y должно быть больше -538");
    } catch (IOException e) {
      pipe.println("Произошла критическая ошибка в ФС при чтении файла. Завершаем программу");
      System.exit(0);
    } catch (FileInvalidSyntaxException e){
      pipe.println(e.getMessage());
      return null;
    } catch (NumberFormatException e) {
      pipe.println("Ошибка разбора файла: ожидался другой тип");
      pipe.println(e.getMessage());
      return null;
    }
    return new Junker(null, new long[]{x}, new double[]{y}, null);
  }

  /**
   * Переопределенный метод, возвращающей сборку из параметров класса, являющегося частью элемента коллекции
   * @return {@link interaction.sender.Prompter.Junker} параметры класса
   */
  @Override public Junker getAddress() {
    try {
      String zipCode = scanLine("Address.zipCode:");
      if (zipCode == null) throw new FileInvalidSyntaxException("Ошибка прочтения строки файла: обнарудена попытка заполнить not-null поле null'ом");
      Junker location = getLocation();
      return new Junker(new Junker[]{location}, null, null, new String[]{zipCode});
    } catch (FileInvalidSyntaxException e) {
      pipe.println(e.getMessage());
      return null;
    } catch (IOException e) {
      pipe.println("Произошла критическая ошибка в ФС при чтении файла. Завершаем программу");
      System.exit(0);
    }
    return null;
  }

  /**
   * Переопределенный метод, возвращающей сборку из параметров класса, являющегося частью элемента коллекции
   * При не успешности сборки -- возврат null.
   * @return экземпляр класса {@link Junker} -- сборник основных параметров класса
   */
  @Override public Junker getLocation() {
    int x = 0; long y = 0; double z = 0;
    try {
      x = Integer.valueOf(scanLine("Location.x:"));
      y = Long.valueOf(scanLine("Location.y:"));
      z = Double.valueOf(scanLine("Location.z:"));
    } catch (IOException e) {
      pipe.println("Произошла критическая ошибка в ФС при чтении файла. Завершаем программу");
      System.exit(0);
    } catch (FileInvalidSyntaxException e) {
      pipe.println(e.getMessage());
      return null;
    } catch (NumberFormatException e) {
      pipe.println("Ошибка разбора файла: ожидался другой тип");
      pipe.println(e.getMessage());
      return null;
    }
    return new Junker(null, new long[]{x, y}, new double[]{z}, null);
  }

  /**
   * Метод, сканирующий файл полностью и вызывающий команды с аргументами и без.
   * @return признак успешности вызова команды в виде булевой константы
   */
  @Override public boolean scan() {
    try {
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.isEmpty()) continue;
        String[] parsed = line.split(" ");
        if (parsed == null || parsed.length < 1) throw new FileInvalidSyntaxException("Ошибка распознания команды. Предоставлены не все данные");
        if (parsed.length == 1) invoke(parsed[0]);
        else if (parsed.length == 2) invoke(parsed[0], parsed[1]);
        else throw new FileInvalidSyntaxException("Ошибка распознания. Неверное число параметров предоставлено.");
      }
      pipe.println("Файл успешно исполнен");
      return true;
    } catch (IOException e) {
      pipe.println("Критическая ошибка в ФС во время чтения");
      return false;
    } catch (FileInvalidSyntaxException e) { pipe.println(e.getMessage()); return false;
    } finally {
      try {
        reader.close();
      } catch (IOException e) {
        pipe.println("Произошла критическая ошибка в ФС при закрытии потока чтения. Завершаем программу");
        System.exit(0);
      }
    }
  }
}
