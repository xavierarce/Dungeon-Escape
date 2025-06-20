package characters.player;

import characters.Character;

public class Player extends Character {
  private int experience;
  private int gold;

  public Player(String name, String type) {
    super(name, 0, 0, 0);

    switch (type.toLowerCase()) {
      case "warrior":
        this.health = 100;
        this.attack = 15;
        this.defense = 10;
        break;
      case "assassin":
        this.health = 80;
        this.attack = 20;
        this.defense = 5;
        break;
      case "tank":
        this.health = 120;
        this.attack = 10;
        this.defense = 15;
        break;
      default:
        this.health = 100;
        this.attack = 10;
        this.defense = 10;
    }

    this.experience = 0;
    this.gold = 0;
  }

  public void gainEXP(int amount) {
    experience += amount;
    System.out.println("You gained " + amount + " EXP.");
  }

  public void gainGold(int amount) {
    gold += amount;
    System.out.println("You gained " + amount + " gold.");
  }

  public int getGold() {
    return gold;
  }

  public int getExperience() {
    return experience;
  }
}
