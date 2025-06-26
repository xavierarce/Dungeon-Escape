package combat;

import actions.IActionProvider;
import actions.ICombatAction;
import characters.Fighter;
import characters.enemy.Enemy;
import characters.player.Player;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FightManagerTest {

  // Simple test double for Fighter
  static class TestFighter implements Fighter {
    private final String name;
    private int health;
    private final int attack;
    private final int defense;

    TestFighter(String name, int health, int attack, int defense) {
      this.name = name;
      this.health = health;
      this.attack = attack;
      this.defense = defense;
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public int getHealth() {
      return health;
    }

    @Override
    public int getAttack() {
      return attack;
    }

    @Override
    public int getDefense() {
      return defense;
    }

    @Override
    public boolean isAlive() {
      return health > 0;
    }

    @Override
    public void takeDamage(int amount) {
      int damageTaken = Math.max(0, amount - defense);
      health = Math.max(0, health - damageTaken);
    }
  }

  @Test
  void fightManager_fightEndsWithPlayerVictory() {
    TestFighter player = new TestFighter("Player", 100, 30, 5);
    TestFighter enemy = new TestFighter("Enemy", 40, 10, 2);

    // Mock actionProvider to always return an attack action
    ICombatAction attackAction = mock(ICombatAction.class);
    when(attackAction.execute(player, enemy)).thenAnswer(invocation -> {
      enemy.takeDamage(player.getAttack());
      return player.getName() + " attacks " + enemy.getName();
    });

    IActionProvider actionProvider = mock(IActionProvider.class);
    when(actionProvider.getAction()).thenReturn(attackAction);

    FightManager fightManager = new FightManager(player, enemy, actionProvider);

    boolean result = fightManager.start();

    assertTrue(result, "Player should win the fight");
    assertFalse(enemy.isAlive(), "Enemy should be dead");
    assertTrue(player.isAlive(), "Player should still be alive");
  }

  @Test
  void defaultFightManagerFactory_createsFightManager() {
    DefaultFightManagerFactory factory = new DefaultFightManagerFactory();

    Player player = new Player("TestPlayer", "warrior");
    Enemy enemy = new Enemy("TestEnemy", 100, 20, 5, 50, 100);
    IActionProvider actionProvider = mock(IActionProvider.class);

    FightManager fightManager = factory.create(player, enemy, actionProvider);

    assertNotNull(fightManager, "Factory should create a FightManager instance");
  }
}
