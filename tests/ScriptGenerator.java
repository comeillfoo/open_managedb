import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class ScriptGenerator {

  private static String genCode(long lines) {
    StringBuilder line = new StringBuilder("");
    Random joker = new Random();
    for (long i = 0; i < lines; i++) {
      int command = joker.nextInt(15);
      switch (command) {
        case 0: line.append("info\n"); break;
        case 1: line.append("show\n"); break;
        case 2: line.append("exit\n"); break;
        case 3: line.append("clear\n"); break;
        case 4: line.append("help\n"); break;
        case 5: line.append("save\n"); break;
        case 6: line.append("max_by_creation_date\n"); break;
        case 7: line.append("remove_key " + joker.nextInt() + "\n"); break;
        case 8: line.append("insert " + joker.nextInt() + "\n" + dataGet()); break;
        case 9: line.append("update " + joker.nextInt() + "\n" + dataGet()); break;
        case 10: line.append("remove_lower\n" + dataGet()); break;
        case 11: line.append("replace_if_greater " + joker.nextInt() + "\n" + dataGet()); break;
        case 12: line.append("replace_if_lower " + joker.nextInt() + "\n" + dataGet()); break;
        default: line.append("show\n"); break;
      }
    }
    line.trimToSize();
    return line.toString();
  }

  private static String dataGet() {
    Random joker = new Random();
    StringBuilder blank = new StringBuilder("");
    blank.append("Organization.name: ");
    String name = nextLine();
    blank.append(name + "\n");
    blank.append("Coordinates.x: ");
    blank.append(joker.nextLong() + "\n");
    blank.append("Coordinates.y: ");
    blank.append(joker.nextDouble() + "\n");
    String fullname = nextLine();
    blank.append("Organization.fullname: ");
    blank.append(fullname + "\n");
    blank.append("Organization.annualTurnover: ");
    blank.append(joker.nextDouble() + "\n");
    blank.append("Organization.employeesCount: ");
    blank.append(joker.nextInt() + "\n");
    blank.append("Organization.type: ");
    switch (joker.nextInt()) {
      case 0: blank.append("public"); break;
      case 1: blank.append("trust"); break;
      case 2: blank.append("open joint stock company"); break;
      case 3: blank.append("private limited company"); break;
      default: blank.append("public"); break;
    }
    blank.append("\n");
    blank.append("Address.zipCode: ");
    blank.append(nextLine() + "\n");
    blank.append("Location.x: ");
    blank.append(joker.nextInt() + "\n");
    blank.append("Location.y: ");
    blank.append(joker.nextInt() + "\n");
    blank.append("Location.z: ");
    blank.append(joker.nextInt() + "\n");
    return blank.toString();
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

  public static void main(String[] args) throws IOException {
    Scanner p = new Scanner(System.in);
    long number = 0;
    do {
      System.out.print("Number of commands: ");
      number = p.nextLong();
      p.nextLine();
    } while (number <= 0);
    System.out.println("Name of file: ");
    String scriptname = p.nextLine();
    String sep = System.getProperty("file.separator");
    Path scriptf = Paths.get("scripts" + sep + scriptname);
    Files.createFile(scriptf);
    Files.write(scriptf, genCode(number).getBytes());
    System.out.println("Script file created and filled: " + scriptname);
  }
}