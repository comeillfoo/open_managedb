package entity;

/**
 * Интерфейс, описывающий требования к классу, который можно поместить в отображение
 * @param <K> ключ коллекции
 */
public interface Mappable<K> {
   /**
    * Геттер для получения ключа элемента коллекции
    * @param <K> ключ в коллекции
    * @return K ключ в отображении
    */
   <K> K getKey();

   /**
    * Геттер, для работы команды filter_contains_name
    * @return String какое-то название
    */
   String getName();
}