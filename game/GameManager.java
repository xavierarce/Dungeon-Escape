package game;

import combat.FightManager;
import characters.enemy.Enemy;
import characters.enemy.EnemyFactory;
import characters.player.Player;
import map.Room;

import java.util.Scanner;

public class GameManager {
  private final int MAP_SIZE = 3;
  private Room[][] dungeon;
  private int playerX = 1;
  private int playerY = 1;
  private int dungeonLevel = 1;
  private Scanner scanner = new Scanner(System.in);
  private Player player;

  public void start() {
    System.out.println("=== Welcome to Dungeon Escape ===");
    System.out.print("Enter your name: ");
    String name = scanner.nextLine();

    String type = chooseClass();
    player = new Player(name, type);

    System.out.println("\nWelcome " + player.getName() + " the " + type + "!");
    generateDungeon();

    while (player.isAlive()) {
      System.out.println("\n--- Dungeon Level " + dungeonLevel + " ---");
      movePlayer();

      Room currentRoom = dungeon[playerY][playerX];
      if (!currentRoom.isVisited()) {
        currentRoom.setVisited(true);

        if (currentRoom.hasEnemy()) {
          Enemy enemy = EnemyFactory.generateEnemy(dungeonLevel);
          FightManager fight = new FightManager(player, enemy);
          boolean won = fight.start();
          if (!won) {
            System.out.println("\n💀 You died. GAME OVER.");
            return;
          }

          player.gainEXP(enemy.getExpReward());
          player.gainGold(enemy.getGoldReward());
        } else {
          System.out.println("🌫️ The room is empty.");
        }
      } else {
        System.out.println("🔁 You've already visited this room.");
      }

      if (currentRoom.isExit()) {
        System.out.println("🚪 You found the exit! Proceeding to the next dungeon...");
        dungeonLevel++;
        playerX = playerY = 1;
        generateDungeon();
      }

      if (!askContinue()) {
        System.out.println("👋 Thanks for playing!");
        break;
      }
    }
  }

  private void movePlayer() {
    while (true) {
      showMap();
      System.out.println("Move: w = up, s = down, a = left, d = right, e = explore room");
      String input = scanner.nextLine().trim().toLowerCase();

      switch (input) {
        case "w":
          if (playerY > 0)
            playerY--;
          else
            System.out.println("❌ Wall to the north.");
          break;
        case "s":
          if (playerY < MAP_SIZE - 1)
            playerY++;
          else
            System.out.println("❌ Wall to the south.");
          break;
        case "a":
          if (playerX > 0)
            playerX--;
          else
            System.out.println("❌ Wall to the west.");
          break;
        case "d":
          if (playerX < MAP_SIZE - 1)
            playerX++;
          else
            System.out.println("❌ Wall to the east.");
          break;
        case "e":
          return; // Explore current room
        default:
          System.out.println("Invalid input.");
      }
    }
  }

  private void showMap() {
    for (int y = 0; y < MAP_SIZE; y++) {
      for (int x = 0; x < MAP_SIZE; x++) {
        if (x == playerX && y == playerY)
          System.out.print("[P]");
        else if (dungeon[y][x].isVisited())
          System.out.print("[x]");
        else
          System.out.print("[ ]");
      }
      System.out.println();
    }
  }

  private void generateDungeon() {
    dungeon = new Room[MAP_SIZE][MAP_SIZE];
    for (int y = 0; y < MAP_SIZE; y++) {
      for (int x = 0; x < MAP_SIZE; x++) {
        dungeon[y][x] = new Room();
      }
    }
    dungeon[MAP_SIZE - 1][MAP_SIZE - 1].setExit(true);
  }

  private boolean askContinue() {
    String choice;
    do {
      System.out.print("Continue exploring? (y/n): ");
      choice = scanner.nextLine().trim().toLowerCase();
    } while (!choice.equals("y") && !choice.equals("n"));

    return choice.equals("y");
  }

  private String chooseClass() {
    String[] classes = { "Warrior", "Assassin", "Tank" };
    int selected = 0;

    while (true) {
      System.out.println("\nChoose your class (use 'w' to move up, 's' to move down, Enter to confirm):");
      for (int i = 0; i < classes.length; i++) {
        System.out.println((i == selected ? "> " : "  ") + classes[i]);
      }

      String input = scanner.nextLine();
      if (input.equalsIgnoreCase("w"))
        selected = (selected - 1 + classes.length) % classes.length;
      else if (input.equalsIgnoreCase("s"))
        selected = (selected + 1) % classes.length;
      else if (input.isEmpty())
        return classes[selected];
      else
        System.out.println("Invalid input. Use 'w', 's', or press Enter.");
    }
  }
}
