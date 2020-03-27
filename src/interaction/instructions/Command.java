package interaction.instructions;

/**
 * Интерфейс комманды для паттерна &lt;Command&gt;. Может содержать поля, как
 * собственные параметры. Является функциональным по случайности, однако, возможно, внесет некоторые удобства при расширении.
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 */
@FunctionalInterface
public interface Command {
  /**
   * Общий метод исполнения для всех исполняемых комманд
   */
  void execute();
}