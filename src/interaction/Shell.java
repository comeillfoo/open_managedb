package interaction;

import entity.Organization;
import interaction.customer.Receiver;
import interaction.customer.TotalCommander;
import interaction.instructions.extended.*;
import interaction.sender.Invoker;
import interaction.sender.ConsolePrompter;
import interaction.instructions.base.*;

/**
 * Shell — это главный класс во всей программе,
 * он объединяет две главные сущности паттерна &laquo;Command&raquo;:
 * <ul>
 *  <li>{@link Invoker}: interface;</li>
 *  <li>{@link Receiver}: interface;</li>
 * </ul>
 * <p>
 *  Также в Shell производятся все действия по подготовке (загрузка коллекции,
 *  определение списка доступных комманд, с указанием входных и выходных потоков и т.п.).
 *  Лишь те команды, которые определены в статическом блоке инициализации смогут исполнятся
 *  приложением.
 * </p>
 * @author Come_1LL_F00 aka Lenar Khannanov
 * @author Leargy aka Anton Sushkevich
 * @see Invoker, Receiver, Prompter, Commander, TotalCommander, ConsolePrompter...
 */
public class Shell {
  private static final String environment = "DBPATH"; // title of environment variable
  private static final Receiver<Integer, Organization> GeneralOperator = new TotalCommander(environment); // includes loading
  private static final Invoker GeneralCaller = new ConsolePrompter(System.out, System.in);
  // starting actions
  static {
    GeneralCaller.signup(Add.NAME, new Add(GeneralOperator));
    GeneralCaller.signup(Clear.NAME, new Clear(GeneralOperator));
    GeneralCaller.signup(Exit.NAME, new Exit(GeneralOperator));
    GeneralCaller.signup(Help.NAME, new Help(GeneralOperator, GeneralCaller.getMainStream()));
    GeneralCaller.signup(Info.NAME, new Info(GeneralOperator, GeneralCaller.getMainStream()));
    GeneralCaller.signup(Insert.NAME, new Insert(GeneralOperator));
    GeneralCaller.signup(RemoveKey.NAME, new RemoveKey(GeneralOperator));
    GeneralCaller.signup(Save.NAME, new Save(GeneralOperator));
    GeneralCaller.signup(Show.NAME, new Show(GeneralOperator, GeneralCaller.getMainStream()));
    GeneralCaller.signup(Update.NAME, new Update(GeneralOperator));
    GeneralCaller.signup(ReplaceIfGreater.NAME, new ReplaceIfGreater(GeneralOperator));
    GeneralCaller.signup(ReplaceIfLower.NAME, new ReplaceIfLower(GeneralOperator));
    GeneralCaller.signup(RemoveLower.NAME, new RemoveLower(GeneralOperator));
    GeneralCaller.signup(MaxByDate.NAME, new MaxByDate(GeneralOperator));
    GeneralCaller.signup(SumOfAnnualTurnover.NAME, new SumOfAnnualTurnover(GeneralOperator, GeneralCaller.getMainStream()));
    GeneralCaller.signup(FilterContainsName.NAME, new FilterContainsName(GeneralOperator));
  }
  public static void main(String[] args) {
    while (true) {
      while (GeneralCaller.scan());
    }
  }
}
