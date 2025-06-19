package game;

import combat.FightManager;
import personnages.EnemyFactory;
import personnages.enemy.Enemy;
import personnages.player.Player;

import java.util.Scanner;

public class GameManager {
  private Player player;
  private int dungeonCount = 0;
  private Scanner scanner = new Scanner(System.in);

  public void start() {
    System.out.println("=== Welcome to Dungeon Escape ===");
    System.out.print("Enter your name: ");
    String name = scanner.nextLine();

    System.out.println("Choose your class: [Warrior, Assassin, Tank]");
    String type = scanner.nextLine();

    player = new Player(name, type);
    System.out.println("Welcome " + player.getName() + " the " + type + "!");

    while (player.isAlive()) {
      dungeonCount++;
      System.out.println("\n--- Dungeon " + dungeonCount + " ---");

      Enemy enemy = EnemyFactory.generateEnemy(dungeonCount);

      FightManager fight = new FightManager(player, enemy);
      boolean won = fight.start();

      if (!won) {
        System.out.println("\n💀 You died. GAME OVER.");
        break;
      }

      player.gainEXP(enemy.getExpReward());
      player.gainGold(enemy.getGoldReward());

      if (dungeonCount % 10 == 0) {
        System.out.println("🏪 You reached a shop!");
        // shop.open(player); // Coming soon
      }

      System.out.print("Continue to next dungeon? (y/n): ");
      String choice = scanner.nextLine();
      if (!choice.equalsIgnoreCase("y")) {
        System.out.println("👋 Thanks for playing!");
        break;
      }
    }
  }
}
