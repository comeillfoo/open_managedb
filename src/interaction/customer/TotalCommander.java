package interaction.customer;

// user-packages
import entity.*;
import exceptions.BlackedFileException;
import interaction.customer.plants.OrganizationBuilder;
import interaction.instructions.extended.Indicator;
import interaction.sender.Invoker;
import interaction.sender.Prompter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.regex.Pattern;

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
 * @see Prompter.Junker
 * @see OrganizationBuilder
 */

public final class TotalCommander extends Commander<Integer, Organization> {
  /**
   * Стандартный конструктор класса, вызывает конструктор супер-класса,
   * передавая ему название переменной окружения, по которой происходит загрузка
   * коллекции.
   * @param envVar строковое название переменной окружения
   */
  public TotalCommander(String envVar) {super(envVar);}

  /**
   * Метод подписки на оповещения
   * @param subscriber подписчик
   */
  @Override public void subscribe(Invoker subscriber) { this.subscriber = subscriber; }

  /**
   * Реализация метода добавления элемента, с вычисляющимся ключом,
   * используется простейший метод добавления из интерфейса Map. По сути -
   * просто обёртка для инкапсулирования некоторых возможностей интерфейса.
   * @param element Organization элемент добавляемый в коллекцию
   */
  @Override public void add(Organization element) { elements.put(element.getKey(), element); notify("Элемент " + element + " был успешно добавлен");}

  /**
   * Релизация метода добавления элемента по специализированному ключу, также
   * обертка над стандартным методом коллекции
   * @param key Integer ключ, по которому добавляется элемент
   * @param element Organization сам добавляемый элемент
   */
  @Override public void add(Integer key, Organization element) {elements.put(key, element); notify("Элемент " + element + " по ключу " + key + " был успешно добавлен");}

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
    String sep = System.getProperty("file.separator"); // получаем разделитель подкаталогов в ФС
    Organizations companies = null;
    String filename = "tutor.xml";
    try {
      // обработка не установленной переменной окружения
      if (environ == null || environ.isEmpty()) {
        System.err.println("Критическая ошибка: название переменной окружения не указано.");
        throw new BlackedFileException("Переменная окружения не указана.\nПрограмма настроена на переменную DBPATH");
      }
      // обработка не инициализированной переменной
      filename = System.getenv(environ) == null || System.getenv(environ).isEmpty()? "tutor.xml" : System.getenv(environ);
      if (filename == null || filename.isEmpty()) {
        System.err.println("Критическая ошибка: переменная окружения не проинициализирована.");
        throw new BlackedFileException("Переменная не проинициализирована.\nИнициализируйте переменную DBPATH");
      }
    } catch (BlackedFileException e) {
      System.err.println("Загрузка коллекции завершилась с ошибкой: ");
      System.err.println(e.getMessage());
      System.exit(0);
    }
    // проверка на то осталась ли коллекция тестовой
    if (filename.equals("tutor.xml")) System.err.println("Внимание вы работаете с тестовой коллекцией");
    // начала обработки файла по имени
    String pathname = System.getProperty("user.dir") + sep + "storage" + sep + filename; // путь до коллекции
    File f = new File(pathname); // файл по пути
    // проверка пустой ли файл
    if (f.length() <= 1) {
      System.err.println("Обнаружен пустой файл: будет загружена пустая коллекция, рекомендуем сохранить её перед выходом.");
      companies = new Organizations(new ArrayList<>());
      return companies.getCompanies();
    }
    try {
      if (checkFile(f) == false){
        System.err.println("Обнаружена ошибка синтаксиса в файле!\nКоллекция не была подгружена!");
        companies = new Organizations(new ArrayList<>());
        return companies.getCompanies();
      }
    } catch (IOException e) { System.err.println(e.getMessage()); }
    Path p = f.toPath(); // путь до файла
    try (// загрузка коллекции по потоку и пути
      InputStream streamin = new FileInputStream(p.toFile());
      InputStreamReader reader = new InputStreamReader(streamin);
    ) {
      BasicFileAttributes bfa = Files.readAttributes(p, BasicFileAttributes.class);
      creationDate = bfa.creationTime().toString(); // получения даты создания файла
      JAXBContext loader = null;
      try {
        // создание загрузчика коллекции
        loader = JAXBContext.newInstance(Organizations.class);
        Unmarshaller subloader = loader.createUnmarshaller();
        companies = (Organizations) subloader.unmarshal(reader); // TODO: "maybe null"
      } catch (JAXBException e) {
        System.err.println(e.getMessage());
        System.err.println(e.getStackTrace());
        System.err.println("Произошла критическая ошибка при загрузке с помощью этой\n" +
                "тупой библиотеки, которая не умеет предотвращать никакие ошибки");
        System.exit(0);
      }
    } catch (FileNotFoundException e) {
      System.err.println("Файл с заданным именем не найден. Будет загружена тестовая коллекция");
      notify("Коллекция успешно загружена");
      return new Organizations(new ArrayList<>()).getCompanies();
    } catch (IOException e) {
      System.err.println("Произошла критическая ошибка в вашей файловой системе.\n" +
          "Запустите ее диагностику с помощью соотвествующей утилиты вашей ОС.");
      System.exit(0);
    }
    notify("Коллекция успешно загружена");
    return companies.getCompanies();
  }

  /**
   * Метод выполняющий проверку на правильное заполнение синтаксиса xml-файла или его наличие.
   * Если не будут заполнены обязательные поля, метод заполняет их значеними по умолчанию.
   * @param tempFile
   * @return true/false
   * @throws IOException
   */
  private boolean checkFile(File tempFile) throws IOException {
    FileReader reader = new FileReader(tempFile);
    Scanner scanner = new Scanner(reader);
    String tempString;
    tempString = scanner.nextLine();
    StringBuilder stringBuilder = new StringBuilder();
    String xmlBasement = tempString;
    Pattern base = Pattern.compile("<\\?xml version=\".*\" encoding=\".*\".*\\?>");
    if(base.matcher(tempString).find()) {
      while (scanner.hasNextLine()) {
        tempString = scanner.nextLine();
        stringBuilder.append(tempString);
      }
    }else {
      return false;
    }
    base = Pattern.compile("</organizations>.*<organizations>");
    if (base.matcher(stringBuilder).find()){
      return false;
    }
    String[] dictinary = new String[]{"id=\"\"","name=\"\""};
    String[] degradedXML = stringBuilder.toString().split(" ");
    stringBuilder = new StringBuilder();
    for(int i = 0; i<dictinary.length; i++){
      for(int j = 0; j < degradedXML.length; j++ ) {
        base = Pattern.compile(dictinary[i]);
        if (base.matcher(degradedXML[j]).find()) {
          String replacemant;
          if (dictinary[i].substring(0, dictinary[i].length() - 3).equals("id")) {
            replacemant = "id=\"9696" + j * 3+"\"";
          } else {
            replacemant = "name=\"unknown" +j/2+"\"";
          }
          System.out.printf("Replasing empty values: %s , to defolt: %s! ", dictinary[i],replacemant);
          System.out.println();
          degradedXML[j] = replacemant;
        }
      }
    }
    String string ;
    for (int i = 0; i< degradedXML.length; i++) {
      string = String.format("%s%n%s",degradedXML[i], " ");
      stringBuilder.append(string);
    }
    FileWriter fileWriter = new FileWriter(tempFile.getName());
    fileWriter.append(xmlBasement.concat(stringBuilder.toString()));
    fileWriter.close();
    return true;
  }

  /**
   * Реализация метода выгрузки хранимой коллекции в файл по той же переменной окружения,
   * что и при загрузке
   */
  @Override public void unload() {
    String sep = System.getProperty("file.separator");
    try (OutputStream streamout = new FileOutputStream(System.getProperty("user.dir") + sep + "storage" + sep + System.getenv(environ));
         OutputStreamWriter writer = new OutputStreamWriter(streamout);
    ) {
      try {
        JAXBContext unloader = JAXBContext.newInstance(Organizations.class);
        Marshaller subunloader = unloader.createMarshaller();
        subunloader.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        subunloader.marshal(new Organizations(elements), writer);
      } catch (JAXBException e) {
        System.err.println("Произошла критическая ошибка при попытке сохранить коллекцию\n" +
            "с помощью этой дурацкой библиотеки");
        System.exit(0);
      }
    } catch (IOException e) {
      System.err.println(e.getMessage());
      System.err.println(e.getStackTrace());
    }
    notify("Сохранение прошло успешно");
  }

  /**
   * Реализация метода очистки хранимой коллекции, так же в своей сути обертка над стандартным
   * методом коллекции, чтобы не допустить к ней полный доступ.
   */
  @Override public void clear() {elements.clear(); notify("Коллекция успешно очищена"); }

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
  public Organization cook(Prompter.Junker committed) {
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
    for (Map.Entry<Integer, Organization> enter : elements.entrySet()) {
      if (id.equals(enter.getValue().getID())) {
        return enter.getKey();
      }
    }
    notify("Элемент по id: " + id  + " не найден");
    return null;
  }
  protected void notify(String message) { subscriber.getMainStream().println(message); }
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
    if(elements.containsKey(key)) {
      elements.remove(key);
      notify("Элемент по ключу: " + key + " успешно удален");
    }else System.err.println("Error: There is no elements with such key!");
  }

  /**
   * Реализация метода обозревающего элементы удовлетворяющие заданному условию.
   * Возвращает многострочный текст из строковых пар ключ-значение в формате,
   * опреденным методом toString
   * @param litmus условие, по которому ищется элемент
   * @return текст с информацией об элементах
   */
  @Override public String survey(Indicator litmus) {
    StringBuilder blank = new StringBuilder("");
    for (Map.Entry<Integer, Organization> enter : elements.entrySet())
      if (litmus.detect(enter.getValue()))
        blank.append("key: " + enter.getKey() + "; value: " + enter.getValue() + "\n");
    return blank.toString();
  }
}