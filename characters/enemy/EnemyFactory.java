package characters.enemy;

public class EnemyFactory {
  public static Enemy generateEnemy(int level) {
    String name = "Goblin " + level;
    int base = level * 10;

    return new Enemy(name, 50 + base, 10 + level, 5 + level / 2, 10 + level * 2, 5 + level * 3);
  }
}