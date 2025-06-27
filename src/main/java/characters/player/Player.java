package characters.player;

import inventory.Armor;
import inventory.Inventory;
import inventory.Weapon;
import characters.Character;

public class Player extends Character {
  private int experience;
  private int gold;
  private Inventory inventory;

  private Weapon equippedWeapon;
  private Armor equippedArmor;

  private int level;
  private int expToNextLevel;

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
    this.level = 1;
    this.expToNextLevel = 100;
    this.inventory = new Inventory();
  }

  public void gainEXP(int amount) {
    experience += amount;
    System.out.println("You gained " + amount + " EXP.");

    while (experience >= expToNextLevel) {
      levelUp();
    }
  }

  private void levelUp() {
    experience -= expToNextLevel;
    level++;
    expToNextLevel = (int) (expToNextLevel * 1.5); // next level needs more EXP

    // Increase base stats on level up
    health += 10;
    attack += 2;
    defense += 2;

    System.out.println("💥 Level up! You're now level " + level + "!");
    System.out.println("Stats increased: +10 HP, +2 ATK, +2 DEF.");
  }

  public int getLevel() {
    return level;
  }

  public int getExpToNextLevel() {
    return expToNextLevel;
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

  public Inventory getInventory() {
    return inventory;
  }

  public void equipWeapon(Weapon weapon) {
    this.equippedWeapon = weapon;
    System.out.println("Equipped weapon: " + weapon.getName());
  }

  public void equipArmor(Armor armor) {
    this.equippedArmor = armor;
    System.out.println("Equipped armor: " + armor.getName());
  }

  @Override
  public int getAttack() {
    return attack + (equippedWeapon != null ? equippedWeapon.getAttackBoost() : 0);
  }

  @Override
  public int getDefense() {
    return defense + (equippedArmor != null ? equippedArmor.getDefenseBoost() : 0);
  }

}
