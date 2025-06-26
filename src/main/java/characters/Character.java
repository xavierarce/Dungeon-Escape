package characters;

public abstract class Character implements Fighter {
    protected String name;
    protected int health;
    protected int attack;
    protected int defense;

    public Character(String name, int health, int attack, int defense) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void takeDamage(int amount) {
        int damage = Math.max(0, amount - defense);
        health -= damage;
        System.out.println(name + " took " + damage + " damage!");
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

}
