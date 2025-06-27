package characters.player;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PlayerTest {

  @Test
  void playerConstructor_ShouldInitializeStatsByType() {
    Player warrior = new Player("Warrior", "warrior");
    assertEquals(150, warrior.getHealth());
    assertEquals(20, warrior.getAttack());
    assertEquals(15, warrior.getDefense());

    Player assassin = new Player("Assassin", "assassin");
    assertEquals(85, assassin.getHealth());
    assertEquals(45, assassin.getAttack());
    assertEquals(10, assassin.getDefense());

    Player tank = new Player("Tank", "tank");
    assertEquals(500, tank.getHealth());
    assertEquals(15, tank.getAttack());
    assertEquals(15, tank.getDefense());

    Player defaultPlayer = new Player("Default", "unknown");
    assertEquals(100, defaultPlayer.getHealth());
    assertEquals(10, defaultPlayer.getAttack());
    assertEquals(10, defaultPlayer.getDefense());
  }

  @Test
  void gainEXP_ShouldIncreaseExperience() {
    Player p = new Player("Test", "warrior");
    p.gainEXP(50);
    assertEquals(50, p.getExperience());
    p.gainEXP(25);
    assertEquals(75, p.getExperience());
  }

  @Test
  void gainGold_ShouldIncreaseGold() {
    Player p = new Player("Test", "warrior");
    p.gainGold(100);
    assertEquals(100, p.getGold());
    p.gainGold(50);
    assertEquals(150, p.getGold());
  }
}
