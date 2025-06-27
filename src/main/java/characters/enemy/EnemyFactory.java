package characters.enemy;

public class EnemyFactory {
  public static Enemy generateEnemy(int level) {
    String name = "Goblin " + level;
    int base = level * 10;

    return new Enemy(name, 50 + base, 10 + level, 5 + level / 2, 10 + level * 2, 5 + level * 3);
  }

  public static Enemy generateBoss(int level) {
    int health = 100 + (level * 15);
    int attack = 15 + (level * 3);
    int defense = 8 + (level * 2);
    int expReward = 100 + (level * 50);
    int goldReward = 200 + (level * 100);
    return new Enemy("Dungeon Boss", health, attack, defense, expReward, goldReward);
  }

}