package combat;

import actions.IActionProvider;
import actions.ICombatAction;
import characters.Fighter;
import characters.enemy.Enemy;
import characters.player.Player;
import input.InputProvider;

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
    Player player = new Player("TestPlayer", "warrior");
    Enemy enemy = new Enemy("TestEnemy", 40, 10, 2, 0, 0);

    ICombatAction attackAction = mock(ICombatAction.class);
    when(attackAction.execute(player, enemy)).thenAnswer(invocation -> {
      enemy.takeDamage(player.getAttack());
      return player.getName() + " attacks " + enemy.getName();
    });

    IActionProvider actionProvider = mock(IActionProvider.class);
    when(actionProvider.getAction()).thenReturn(attackAction);

    InputProvider inputProvider = mock(InputProvider.class);
    when(inputProvider.nextLine()).thenReturn("n");

    FightManager fightManager = new FightManager(player, enemy, actionProvider, inputProvider);

    boolean result = fightManager.start();

    assertTrue(result);
    assertFalse(enemy.isAlive());
    assertTrue(player.isAlive());
  }

}
