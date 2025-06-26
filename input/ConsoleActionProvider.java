package input;

import actions.*;
import java.util.Scanner;

public class ConsoleActionProvider implements InputProvider, IActionProvider {
  private Scanner scanner = new Scanner(System.in);

  @Override
  public String nextLine() {
    return scanner.nextLine();
  }

  @Override
  public IActionProvider getActionProvider() {
    return this;
  }

  @Override
  public ICombatAction getAction() {
    while (true) {
      System.out.print("Choose action: [1] Attack, [2] Defend\n> ");
      String input = scanner.nextLine().trim();
      switch (input) {
        case "1":
          return new AttackAction();
        case "2":
          return new DefendAction();
        default:
          System.out.println("Invalid choice.");
      }
    }
  }
}
