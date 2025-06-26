package characters.enemy;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class EnemyFactoryTest {

  @Test
  void generateEnemy_ShouldCreateEnemyWithCorrectStats() {
    int level = 3;
    Enemy enemy = EnemyFactory.generateEnemy(level);

    assertEquals("Goblin 3", enemy.getName());
    assertEquals(50 + level * 10, enemy.getHealth());
    assertEquals(10 + level, enemy.getAttack());
    assertTrue(enemy.getExpReward() > 0);
    assertTrue(enemy.getGoldReward() > 0);
  }

  @Test
  void generateBoss_ShouldCreateBossWithCorrectStats() {
    int level = 2;
    Enemy boss = EnemyFactory.generateBoss(level);

    assertEquals("Dungeon Boss", boss.getName());
    assertEquals(150 + level * 20, boss.getHealth());
    assertEquals(30 + level * 5, boss.getAttack());
    assertEquals(100 + level * 50, boss.getExpReward());
    assertEquals(200 + level * 100, boss.getGoldReward());
  }
}
