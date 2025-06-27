package map;

import characters.enemy.Enemy;
import characters.enemy.EnemyFactory;

public class Dungeon {
  private final int MAP_SIZE = 5;
  private Room[][] rooms;
  private int bossX;
  private int bossY;
  private boolean bossDefeated;
  private Enemy boss;

  public Dungeon(int playerX, int playerY, int dungeonLevel) {
    generateDungeon(playerX, playerY, dungeonLevel);
  }

  private void generateDungeon(int playerX, int playerY, int dungeonLevel) {
    rooms = new Room[MAP_SIZE][MAP_SIZE];

    for (int y = 0; y < MAP_SIZE; y++) {
      for (int x = 0; x < MAP_SIZE; x++) {
        rooms[y][x] = new Room();
        if (!(x == playerX && y == playerY) && !(x == MAP_SIZE - 1 && y == MAP_SIZE - 1)) {
          boolean hasEnemy = Math.random() < 0.7;
          rooms[y][x].setHasEnemy(hasEnemy);
        }
      }
    }

    rooms[MAP_SIZE - 1][MAP_SIZE - 1].setExit(true);

    bossY = 0;
    bossX = MAP_SIZE / 2;
    rooms[bossY][bossX].setBossRoom(true);

    boss = EnemyFactory.generateBoss(dungeonLevel);
    bossDefeated = false;
  }

  public Room getRoom(int x, int y) {
    return rooms[y][x];
  }

  public boolean isBossRoom(int x, int y) {
    return x == bossX && y == bossY;
  }

  public boolean isExit(int x, int y) {
    return rooms[y][x].isExit();
  }

  public Enemy getBoss() {
    return boss;
  }

  public boolean isBossDefeated() {
    return bossDefeated;
  }

  public void setBossDefeated(boolean defeated) {
    this.bossDefeated = defeated;
  }

  public int getMapSize() {
    return MAP_SIZE;
  }

  public Room[][] getRooms() {
    return rooms;
  }
}
