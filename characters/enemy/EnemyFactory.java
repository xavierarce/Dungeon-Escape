package characters.enemy;

public class EnemyFactory {
  public static Enemy generateEnemy(int level) {
    String name = "Goblin " + level;
    int base = level * 10;

    return new Enemy(name, 50 + base, 10 + level, 5 + level / 2, 10 + level * 2, 5 + level * 3);
  }

  public static Enemy generateBoss(int level) {
    int health = 150 + (level * 20);
    int attack = 30 + (level * 5);
    int defense = 15 + (level * 3);
    int expReward = 100 + (level * 50);
    int goldReward = 200 + (level * 100);
    return new Enemy("Dungeon Boss", health, attack, defense, expReward, goldReward);
  }

}