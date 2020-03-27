import interaction.customer.plants.OrganizationBuilder;
import interaction.sender.ConsolePrompter;
import interaction.sender.Prompter;

import java.util.Random;

public class FacilityTester {
  public static void main(String[] args) {
    test1();
    System.out.println("Test 1 completed");
    test2();
    System.out.println("Test 2 completed");
    test3();
    System.out.println("Test 3 completed");
    test4();
    System.out.println("Test 4 completed");
    test5();
    System.out.println("Test 5 completed");
    test6();
    System.out.println("Test 6 completed");
  }

  private static void test1() {
    new OrganizationBuilder().make(null);
  }
  private static void test2() {
    Prompter.Junker link = new ConsolePrompter(System.out, System.in).new Junker(null, null, null, null);
    new OrganizationBuilder().make(link);
  }

  private static void test3() {
    Prompter.Junker link = new ConsolePrompter(System.out, System.in).new Junker(null, null, null, new String[] {"public"});
    new OrganizationBuilder().make(link);
  }

  private static void test4() {
    Prompter.Junker link = new ConsolePrompter(System.out, System.in).new Junker(null, null, null, new String[] {"public"});
    new OrganizationBuilder().make(link);
  }

  private static void test5() {
    String name = nextLine();
    String fullname = nextLine();
    float annualTurnover = new Random().nextFloat();
    int employeesCount = new Random().nextInt();
    Prompter.Junker link = new ConsolePrompter(System.out, System.in).new Junker(null, new long[]{employeesCount}, new double[]{annualTurnover}, new String[]{name, fullname, "public"});
    new OrganizationBuilder().make(link);
  }

  private static void test6() {
    Random joker = new Random();
    String name = nextLine();
    String fullname = nextLine();
    float annualTurnover = new Random().nextFloat();
    int employeesCount = new Random().nextInt();
    Prompter.Junker location = new ConsolePrompter(System.out, System.in).new Junker(null, new long[]{joker.nextInt(), joker.nextInt()}, new double[]{joker.nextDouble()}, null);
    Prompter.Junker officialAddress = new ConsolePrompter(System.out, System.in).new Junker(new Prompter.Junker[]{location}, null, null, new String[]{nextLine()});
    Prompter.Junker coordinates = new ConsolePrompter(System.out, System.in).new Junker(null, new long[]{joker.nextInt()}, new double[]{joker.nextDouble()}, null);
    Prompter.Junker link = new ConsolePrompter(System.out, System.in).new Junker(new Prompter.Junker[]{coordinates, officialAddress}, new long[]{employeesCount}, new double[]{annualTurnover}, new String[]{name, fullname, nextLine()});
    new OrganizationBuilder().make(link);
  }

  private static String nextLine() {
    Random joker = new Random();
    int upper = joker.nextInt(255);
    StringBuilder line = new StringBuilder("");
    for (int i = 0; i < upper; i++)
      line.append(joker.nextInt());
    line.trimToSize();
    return line.toString();
  }
}
