import interaction.customer.TotalCommander;

public class CommanderTester {
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
  }

  static void test1() {
    new TotalCommander(null);
  }
  static void test2() {
    new TotalCommander("");
  }
  static void test3() {
    new TotalCommander("FUCKINGVARIABLE");
  }
  static void test4() {
    new TotalCommander(new Long(10321L).toString());
  }
  static void test5() {
    TotalCommander total = new TotalCommander("DBPATH");
    total.clear();
    total.unload();
  }
}
