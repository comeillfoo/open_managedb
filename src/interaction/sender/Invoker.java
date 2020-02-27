package interaction.sender;

import interaction.instructions.Command;
import java.io.PrintStream;

/**
 * Интерфейс Invoker — часть паттерна "Команда", который отвечает вызывает все доступные команды по имени;
 * по совместительству — обработчик всех прихотей пользователя т.е. организует работу с ним в интерактивном режиме.
 * <p>
 *  Только классы этого интерфейса определяют: экземпляр какой команды отправить Receiver'у следующей на исполнение.
 *  Также хранит поток, через который общается с пользователем.
 * </p>
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Prompter, ConsolePrompter, FilePrompter, Receiver, Commander, TotalCommander
 */
public interface Invoker {
  void signup(String command_name, Command instruct);
  boolean scan();
  PrintStream getMainStream();
}
