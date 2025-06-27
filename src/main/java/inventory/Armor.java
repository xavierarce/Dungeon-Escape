package inventory;

public class Armor extends Item {
  private int defenseBoost;

  public Armor(String name, int defenseBoost) {
    super(name);
    this.defenseBoost = defenseBoost;
  }

  public int getDefenseBoost() {
    return defenseBoost;
  }
}