package characters.enemy;

import characters.Character;

public class Enemy extends Character {
  private int expReward;
  private int goldReward;

  public Enemy(String name, int health, int attack, int defense, int expReward, int goldReward) {
    super(name, health, attack, defense);
    this.expReward = expReward;
    this.goldReward = goldReward;
  }

  public int getExpReward() {
    return expReward;
  }

  public int getGoldReward() {
    return goldReward;
  }
}
