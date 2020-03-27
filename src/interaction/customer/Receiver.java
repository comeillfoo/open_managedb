package interaction.customer;

import entity.Mappable;
import interaction.instructions.extended.Indicator;
import interaction.sender.Invoker;
import interaction.sender.Prompter;

import java.io.PrintStream;
import java.util.List;

/**
 * Интерфейс - часть паттерна "Команда", классы которого реализуют всю бизнес-логику
 * обработки коллекции.
 * @param <K> тип используемого в коллекции ключа
 * @param <V> тип элемента коллекции
 */
public interface Receiver<K, V extends Mappable<K>> {
  /**
   * Простейший метод, добавляющий элемент в коллекцию,
   * Ключ вычисляется автоматически
   * @param element добавляемый элемент в коллекцию
   */
  void add(V element);

  /**
   * Дополнительный метод, добавляющий элемент по заданному ключу
   * @param key ключ, по которому добавляется элемент
   * @param element сам добавляемый элемент
   */
  void add(K key, V element);

  /**
   * Расширенный метод, учитывающий дополнительно условие добавления элемента в коллекцию
   * @param key ключ, по которому добавляется элемент
   * @param element добавляемый элемент
   * @param sign условие, по которому элемент должен добавится в коллекцию
   */
  void add(K key, V element, Indicator sign);

  /**
   * Метод загрузки коллекции из файла в формате XML
   * @return Список элементов, считанной коллекции
   */
  List<V> load();

  /**
   * Метод сохранения коллекции в файл XML
   */
  void unload();

  /**
   * Метод, очищающий коллекцию
   */
  void clear();

  /**
   * Метод, выводящий справку о доступных командах, жестко
   * связан с командой Help
   * @param pages массив описания комманд (каждый элемент - одна команда)
   * @param printer поток вывода информации
   */
  void man(String[] pages, PrintStream printer);

  /**
   * Метод, возвращающий текстовую информацию о хранящейся коллекции
   * @return строка информации о коллекции
   */
  String review();

  /**
   * Метод, возвращающий собранный объект из сериализованных параметров
   * @param committed объект, хранящий параметры о собранном элементе
   * @return собранный из параметров элемент коллекции
   */
  Mappable<K> cook(Prompter.Junker committed);

  /**
   * Метод поиска ключа элемента по его id (идентификатору)
   * @param id идентификатор элемента
   * @return ключ элемента коллекции
   */
  K search(Integer id);

  /**
   * Метод удаления элемента по заданному ключу
   * @param key ключ элемента
   */
  void remove(K key);

  /**
   * Метод, возращающий информацию об элементе, если тот удовлетворяет
   * заданному условию
   * @param litmus условие, по которому ищется элемент
   * @return строка с информацией об элементах
   */
  String survey(Indicator litmus);

  /**
   * Метод поиска элемента по ключу, возвращает ссылку на элемент
   * @param key ключ, по которому производится поиск
   * @return ссылка на элемент коллекции
   */
  V lookFor(K key);

  /**
   * Метод подписки на оповещения о действия класса
   * @param subscriber подписчик
   */
  void subscribe(Invoker subscriber);
}