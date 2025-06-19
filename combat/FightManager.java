package combat;

import personnages.enemy.*;
import personnages.player.*;

import java.util.Scanner;

public class FightManager {
  private Player player;
  private Enemy enemy;
  private Scanner scanner = new Scanner(System.in);

  public FightManager(Player player, Enemy enemy) {
    this.player = player;
    this.enemy = enemy;
  }

  public boolean start() {
    System.out.println("⚔️ A wild " + enemy.getName() + " appears!");

    while (player.isAlive() && enemy.isAlive()) {
      System.out.println("\nYour HP: " + player.getHealth() + " | Enemy HP: " + enemy.getHealth());
      System.out.print("Choose action: [1] Attack\n> ");
      scanner.nextLine(); // Only one option now

      // Player attacks
      enemy.takeDamage(player.getAttack());
      if (!enemy.isAlive()) {
        System.out.println("🎉 You defeated the " + enemy.getName() + "!");
        return true;
      }

      // Enemy attacks
      System.out.println(enemy.getName() + " attacks!");
      player.takeDamage(enemy.getAttack());
    }

    return false;
  }
}
