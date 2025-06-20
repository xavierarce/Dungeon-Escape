package actions;

import java.util.Scanner;

public class ConsoleActionProvider implements IActionProvider {
  private Scanner scanner = new Scanner(System.in);

  @Override
  public int getAction() {
    while (true) {
      System.out.print("Choose action: [1] Attack\n> ");
      String input = scanner.nextLine().trim();
      if (input.equals("1")) {
        return 1;
      }
      System.out.println("Invalid choice. Please enter '1'.");
    }
  }
}
