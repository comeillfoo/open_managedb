package interaction.customer;

// user-packages
import entity.*;
import interaction.customer.plants.OrganizationBuilder;
import interaction.instructions.extended.Indicator;
import interaction.sender.Prompter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.util.List;

// io package
import java.io.IOException;
import java.io.PrintStream;
// input:
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
// output:
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.util.Map;

/**
 * Класс, реализующий интерфейс Receiver. Предоставляет реализацию всем методам, которые используются
 * для исполнения различных простых и не очень комманд.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Receiver
 * @see Commander
 * @see interaction.sender.Invoker
 * @see Prompter
 * @see interaction.sender.ConsolePrompter
 * @see Prompter.ParamsCollector
 * @see OrganizationBuilder
 */
public class TotalCommander extends Commander<Integer, Organization> {
  /**
   * Стандартный конструктор класса, вызывает конструктор супер-класса,
   * передавая ему название переменной окружения, по которой происходит загрузка
   * коллекции.
   * @param envVar строковое название переменной окружения
   */
  public TotalCommander(String envVar) {super(envVar);}

  /**
   * Реализация метода добавления элемента, с вычисляющимся ключом,
   * используется простейший метод добавления из интерфейса Map. По сути -
   * просто обёртка для инкапсулирования некоторых возможностей интерфейса.
   * @param element Organization элемент добавляемый в коллекцию
   */
  @Override public void add(Organization element) { elements.put(element.getKey(), element);}

  /**
   * Релизация метода добавления элемента по специализированному ключу, также
   * обертка над стандартным методом коллекции
   * @param key Integer ключ, по которому добавляется элемент
   * @param element Organization сам добавляемый элемент
   */
  @Override public void add(Integer key, Organization element) {elements.put(key, element);}

  /**
   * Реализация метода добавления элемента, учитывающий также условие добавления элемента,
   * который передается как реализация функционального интерфейса
   * @param key Integer ключ, по которому добавляется элемент
   * @param element Organization добавляемый элемент
   * @param sign Indicator условие, по которому элемент должен добавится в коллекцию
   */
  @Override
  public void add(Integer key, Organization element, Indicator sign) {
    if (sign.detect(lookFor(key)))
      add(key, element);
  }

  /**
   * Реализация метода загрузки коллекции из файла, название которого хранится в переменной окружения,
   * считается, что файл лежит в корне проекта (директория managedb). Использует внешнюю библиотеку JAXB для
   * парсинга файла и формирования списка.
   * @return List&lt;Organization&gt; список элементов коллекции, считанной из файла
   */
  @Override public List<Organization> load() {
    Organizations companies = null; // TODO: eliminate "maybe null" remaining
    try(InputStream streamin = new FileInputStream(System.getProperty("user.dir") + "/" + System.getenv(envVar));
        InputStreamReader reader = new InputStreamReader(streamin);
    ) {
      JAXBContext loader = null;
      try {
        loader = JAXBContext.newInstance(Organizations.class);
        Unmarshaller subloader = loader.createUnmarshaller();
        companies = (Organizations) subloader.unmarshal(reader); // TODO: "maybe null"
      } catch (JAXBException e) {
        System.err.println(e.getErrorCode() + ": " + e.getMessage());
        System.err.println(e.getStackTrace());
        System.exit(1);
      }
    } catch (IOException e) {
      System.err.println(e.getMessage());
      System.err.println(e.getStackTrace());
      System.exit(1);
    }
    return companies.getCompanies();
  }

  /**
   * Реализация метода выгрузки хранимой коллекции в файл по той же переменной окружения,
   * что и при загрузке
   */
  @Override public void unload() {
    try(OutputStream streamout = new FileOutputStream(System.getenv(envVar));
        OutputStreamWriter writer = new OutputStreamWriter(streamout);
    ) {
      try {
        JAXBContext unloader = JAXBContext.newInstance(Organizations.class);
        Marshaller subunloader = unloader.createMarshaller();
        subunloader.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        subunloader.marshal(new Organizations(elements), writer);
      } catch (JAXBException e) {
        System.err.println(e.getErrorCode() + ": " + e.getMessage());
        System.err.println(e.getStackTrace());
      }
    } catch (IOException e) {
      System.err.println(e.getMessage());
      System.err.println(e.getStackTrace());
    }
  }

  /**
   * Реализация метода очистки хранимой коллекции, так же в своей сути обертка над стандартным
   * методом коллекции, чтобы не допустить к ней полный доступ.
   */
  @Override public void clear() {elements.clear();}

  /**
   * Реализация метода вывода справки по коммандам. По сути передача управления вывода от
   * команды к Receiver'у для соответсвия паттерну и принципу SRP.
   * @param pages массив описания комманд (каждый элемент - одна команда)
   * @param writer поток вывода
   */
  @Override
  public void man(String[] pages, PrintStream writer) {
    for (String p : pages)
      writer.println(p);
  }
  // There is a facility magic
  /**
   * Реализация метода подготовки объекта коллекции на основе кучи разрозненных параметров.
   * В общем случае является посредником между Invoke'ом (кое-как вытянувшим нормальные параметры у тупого пользователя)
   * и фабрикой, которая в свою очередь и берет изготовление и расшифровку параметров на себя.
   * Не рекомендован к самостоятельному пользованию и по сути должен быть закрыт от общего доступа.
   * @param committed объект, хранящий параметры о собранном элементе
   * @return Organization экземпляр объекта собранного фабрикой
   */
  @Override
  public Organization cook(Prompter.ParamsCollector committed) {
    return new OrganizationBuilder().make(committed);
  }
  /**
   * Реализация метода поиска ключа по идентификатору (возможно получить null при отсутствии элемента
   * с заданным id)
   * @param id идентификатор элемента
   * @return Integer ключ элемента отображения
   */
  @Override
  public Integer search(Integer id) {
    for (Map.Entry<Integer, Organization> enter : elements.entrySet())
      if (id.equals(enter.getValue().getID()))
        return enter.getKey();
    return null;
  }

  /**
   * Реализация метода поиска элемента по ключу.
   * Также пока просто прослойка между стандартным методом коллекции
   * @param key Integer ключ, по которому производится поиск
   * @return Organization ссылка на элемент с заданным ключом
   */
  @Override
  public Organization lookFor(Integer key) {
    return elements.getOrDefault(key, new Organization());
  }

  /**
   * Реализация метода удаления элемента по ключу.
   * Просто прослойка между стандартным методом коллекции
   * @param key Integer ключ элемента
   */
  @Override
  public void remove(Integer key) {
    elements.remove(key);
  }

  /**
   * Реализация метода обозревающего элементы удовлетворяющие заданному условию.
   * Возвращает многострочный текст из строковых пар ключ-значение в формате,
   * опреденным методом toString
   * @param litmus условие, по которому ищется элемент
   * @return текст с информацией об элементах
   */
  @Override
  public String survey(Indicator litmus) {
    StringBuilder blank = new StringBuilder("");
    for (Map.Entry<Integer, Organization> enter : elements.entrySet())
      if (litmus.detect(enter.getValue()))
        blank.append("key: " + enter.getKey() + "; value: " + enter.getValue() + "\n");
    return blank.toString();
  }
}