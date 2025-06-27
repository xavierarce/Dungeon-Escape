package inventory;

public class Weapon extends Item {
  private int attackBoost;

  public Weapon(String name, int attackBoost) {
    super(name);
    this.attackBoost = attackBoost;
  }

  public int getAttackBoost() {
    return attackBoost;
  }
}