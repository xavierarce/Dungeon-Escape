package combat;

import java.util.Scanner;
import characters.Fighter; 

public class FightManager {
  private Fighter player;
  private Fighter enemy;
  private Scanner scanner = new Scanner(System.in);

  public FightManager(Fighter player, Fighter enemy) {
    this.player = player;
    this.enemy = enemy;
  }

  public boolean start() {
    System.out.println("⚔️ A wild " + enemy.getName() + " appears!");

    while (player.isAlive() && enemy.isAlive()) {
      showStatus();
      int action = askAction();

      if (action == 1) {
        playerAttack();
        if (!enemy.isAlive()) {
          System.out.println("🎉 You defeated the " + enemy.getName() + "!");
          return true;
        }

        enemyAttack();
      }
    }

    return false;
  }

  private void showStatus() {
    System.out.println("\nYour HP: " + player.getHealth() + " | Enemy HP: " + enemy.getHealth());
  }

  private int askAction() {
    while (true) {
      System.out.print("Choose action: [1] Attack\n> ");
      String input = scanner.nextLine().trim();
      if (input.equals("1")) {
        return 1;
      }
      System.out.println("Invalid choice. Please enter '1'.");
    }
  }

  private void playerAttack() {
    enemy.takeDamage(player.getAttack());
  }

  private void enemyAttack() {
    System.out.println(enemy.getName() + " attacks!");
    player.takeDamage(enemy.getAttack());
  }
}
