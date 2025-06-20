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

    String[] classes = { "Warrior", "Assassin", "Tank" };
    int selected = 0;

    while (true) {
      System.out.println("\nChoose your class (use 'w' to move up, 's' to move down, press Enter to confirm):");
      for (int i = 0; i < classes.length; i++) {
        if (i == selected) {
          System.out.println("> " + classes[i]);
        } else {
          System.out.println("  " + classes[i]);
        }
      }

      String input = scanner.nextLine();

      if (input.equalsIgnoreCase("w")) {
        selected = (selected - 1 + classes.length) % classes.length;
      } else if (input.equalsIgnoreCase("s")) {
        selected = (selected + 1) % classes.length;
      } else if (input.isEmpty()) { // juste appuyer sur Entrée pour valider
        break;
      } else {
        System.out.println("Invalid input. Use 'w', 's', or press Enter.");
      }
    }

    String type = classes[selected];
    player = new Player(name, type);
    System.out.println("\nWelcome " + player.getName() + " the " + type + "!");

    // Boucle principale du jeu inchangée
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
