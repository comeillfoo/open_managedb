package interaction;

import interaction.customer.Commander;

public class Shell {
  public static void main(String[] args) {
    Commander manager = new TotalCommander("DBPATH");

  }
}