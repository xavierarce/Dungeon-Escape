package personnages;

public abstract class Personnage {
  protected String name;
  protected int health;
  protected int attack;
  protected int defense;

  public Personnage(String name, int health, int attack, int defense) {
    this.name = name;
    this.health = health;
    this.attack = attack;
    this.defense = defense;
  }

  public boolean isAlive() {
    return health > 0;
  }

  public void takeDamage(int amount) {
    int damage = Math.max(0, amount - defense);
    health -= damage;
    System.out.println(name + " took " + damage + " damage!");
  }

  public String getName() {
    return name;
  }

  public int getHealth() {
    return health;
  }

  public int getAttack() {
    return attack;
  }
}
