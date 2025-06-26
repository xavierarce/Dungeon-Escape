package characters.enemy;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class EnemyTest {

  @Test
  void testTakeDamage_ShouldReduceHealthConsideringDefense() {
    Enemy enemy = new Enemy("Test Enemy", 100, 20, 5, 10, 20);

    enemy.takeDamage(15); // damage - defense = 10
    assertEquals(90, enemy.getHealth());

    enemy.takeDamage(3); // damage - defense = 0, minimum 0 damage
    assertEquals(90, enemy.getHealth()); // health unchanged

    enemy.takeDamage(100);
    assertTrue(enemy.getHealth() <= 0);
    assertFalse(enemy.isAlive());
  }

  @Test
  void testGetExpAndGoldRewards() {
    Enemy enemy = new Enemy("Test", 100, 20, 5, 50, 100);
    assertEquals(50, enemy.getExpReward());
    assertEquals(100, enemy.getGoldReward());
  }
}
