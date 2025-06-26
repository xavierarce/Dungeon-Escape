package actions;

import characters.Fighter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DefendActionTest {

  static class TestFighter implements Fighter {
    private final String name;

    TestFighter(String name) {
      this.name = name;
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public int getHealth() {
      return 100;
    }

    @Override
    public int getAttack() {
      return 10;
    }

    public int getDefense() {
      return 0; // ou une valeur que tu veux simuler dans tes tests
    }

    @Override
    public boolean isAlive() {
      return true;
    }

    @Override
    public void takeDamage(int amount) {
      // No-op for test
    }
  }

  @Test
  void defendShouldReturnCorrectMessage() {
    TestFighter player = new TestFighter("Hero");
    TestFighter enemy = new TestFighter("Goblin");

    DefendAction defend = new DefendAction();
    String result = defend.execute(player, enemy);

    assertTrue(result.contains("Hero defends and braces"), "Defend message should contain player name and action");
  }
}
