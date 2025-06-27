package game;

import input.InputProvider;
import combat.FightManager;
import combat.FightManagerFactory;
import characters.enemy.Enemy;
import characters.enemy.EnemyFactory;
import characters.player.Player;
import map.Room;
import map.Dungeon;

public class GameManager {
  private int playerX = 1;
  private int playerY = 1;
  private int dungeonLevel = 1;
  private final InputProvider inputProvider;
  private final FightManagerFactory fightFactory;
  private Player player;
  private Dungeon dungeon;

  public GameManager(InputProvider inputProvider, FightManagerFactory fightFactory) {
    this.inputProvider = inputProvider;
    this.fightFactory = fightFactory;
  }

  public void start() {
    System.out.println("=== Welcome to Dungeon Escape ===");
    System.out.print("Enter your name: ");
    String name = inputProvider.nextLine();

    String type = chooseClass();
    player = new Player(name, type);

    System.out.println("\nWelcome " + player.getName() + " the " + type + "!");
    dungeon = new Dungeon(playerX, playerY, dungeonLevel);

    while (player.isAlive()) {
      System.out.println(
          "\n--- Dungeon Level " + dungeonLevel + " ---" +
              "\n👤 Player Level: " + player.getLevel() +
              " | EXP: " + player.getExperience() + "/" + player.getExpToNextLevel() +
              "\n❤️ Health: " + player.getHealth() + "/" + player.getMaxHealth() +
              "\n⚔️ Attack: " + player.getAttack() +
              "\n🛡️ Defense: " + player.getDefense() +
              "\n💰 Gold: " + player.getGold() +
              "\n🔫 Equipped Weapon: " + player.getEquippedWeaponName() +
              "\n🛡️ Equipped Armor: " + player.getEquippedArmorName());
      movePlayer();

      Room currentRoom = dungeon.getRoom(playerX, playerY);

      if (!currentRoom.isVisited()) {
        currentRoom.setVisited(true);

        if (currentRoom.isBossRoom() && !dungeon.isBossDefeated()) {
          System.out.println("!! You encountered the Dungeon Boss !!");
          FightManager fight = fightFactory.create(player, dungeon.getBoss(), inputProvider.getActionProvider(),
              inputProvider);
          if (!fight.start()) {
            System.out.println("\n You died. GAME OVER.");
            return;
          }
          dungeon.setBossDefeated(true);
          player.gainEXP(dungeon.getBoss().getExpReward());
          player.gainGold(dungeon.getBoss().getGoldReward());
          System.out.println(" You defeated the boss!");
        } else if (currentRoom.hasEnemy()) {
          Enemy enemy = EnemyFactory.generateEnemy(dungeonLevel);
          FightManager fight = fightFactory.create(player, enemy, inputProvider.getActionProvider(), inputProvider);
          if (!fight.start()) {
            System.out.println("\n You died. GAME OVER.");
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
        if (dungeon.isBossDefeated()) {
          System.out.println("🚪 You found the exit and can now proceed to the next dungeon.");
          dungeonLevel++;
          playerX = playerY = 1;
          dungeon = new Dungeon(playerX, playerY, dungeonLevel);
        } else {
          System.out.println("🚪 You found the exit but the boss is still alive! Defeat the boss first!");
        }
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
      String input = inputProvider.nextLine().trim().toLowerCase();

      switch (input) {
        case "w":
          if (playerY > 0)
            playerY--;
          else
            System.out.println("❌ Wall to the north.");
          break;
        case "s":
          if (playerY < dungeon.getMapSize() - 1)
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
          if (playerX < dungeon.getMapSize() - 1)
            playerX++;
          else
            System.out.println("❌ Wall to the east.");
          break;
        case "e":
          return;
        default:
          System.out.println("Invalid input.");
      }
    }
  }

  private void showMap() {
    Room[][] rooms = dungeon.getRooms();
    for (int y = 0; y < dungeon.getMapSize(); y++) {
      for (int x = 0; x < dungeon.getMapSize(); x++) {
        if (x == playerX && y == playerY)
          System.out.print("[P]");
        else if (rooms[y][x].isBossRoom() && !dungeon.isBossDefeated())
          System.out.print("[B]");
        else if (rooms[y][x].isExit())
          System.out.print("[E]");
        else if (rooms[y][x].isVisited())
          System.out.print("[x]");
        else
          System.out.print("[ ]");
      }
      System.out.println();
    }
  }

  private boolean askContinue() {
    String choice;
    do {
      System.out.print("Continue exploring? (y/n): ");
      choice = inputProvider.nextLine().trim().toLowerCase();
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

      String input = inputProvider.nextLine();
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
