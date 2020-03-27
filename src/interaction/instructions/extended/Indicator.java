package interaction.instructions.extended;

import entity.Mappable;

/**
 * Функциональный интерфейс, для проверки элементов.
 * Обычно передается в виде параметра лямбдой, указывающий порядок
 * проверки элемента.
 */
@FunctionalInterface
public interface Indicator {
  /**
   * Метод проверки элемента.
   * @param subject проверяемый элемент
   * @return признак удовлетворения элементом условия
   */
  boolean detect(Mappable<Integer> subject);
}
