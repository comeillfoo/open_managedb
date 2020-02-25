package interaction;

import entity.Organization;
import interaction.customer.Reciever;
import interaction.customer.TotalCommander;
import interaction.sender.Invoker;
import interaction.sender.ConsolePrompter;
import interaction.instructions.base.*;

public class Shell {
  private final String environment = "DBPATH"; // title of environment variable
  private final Reciever<Integer, Organization> GeneralOperator = new TotalCommander(environment); // includes loading
  private static final Invoker GeneralCaller = new ConsolePrompter(System.out, System.in);
  // starting actions
  {
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
    // TODO: sign up extended instructions...
  }
  public static void main(String[] args) {
    while (true) {
      while (GeneralCaller.scan()) ;
    }
  }
}