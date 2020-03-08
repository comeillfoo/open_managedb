package interaction.customer;

import entity.Mappable;
import java.util.HashMap;
import java.util.Map;

/**
 * Абстрактный обобщенный класс для управления коллекцией
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @param <K> тип ключа в коллекции
 * @param <V> тип элемента коллекции
 * @see Receiver
 */
abstract class Commander<K, V extends Mappable<K>> implements Receiver<K, V> {
  protected final Map<K, V> elements;
  protected final String envVar;

  /**
   * Конструктор, для создания и загрузки коллекции из файла,
   * которое передается в переменной окружения, название которой передается
   * в качестве параметра
   * @param envVar String название переменной окружения, хранящей название файла
   */
  protected Commander(String envVar) {
    this.envVar = envVar;
    elements = new HashMap<>();
    for (V element : load())
      add(element);
  }

  /**
   * Переопределеный метод, дающий общую информацию о коллекции
   * @return String базовая информация о хранящейся коллекции
   */
  @Override public String review() {
    Class studyee = elements.getClass();
    StringBuilder info = new StringBuilder("\t* Collection canonical name: " + studyee.getCanonicalName() + ";\n");
    info.append("\t* Collection type name: " + studyee.getTypeName() + ";\n");
    info.append("\t* Is collection empty?: " + elements.isEmpty() + ";\n");
    info.append("\t* Collection size: " + elements.size() + ";\n");
    return info.toString();
  }
}