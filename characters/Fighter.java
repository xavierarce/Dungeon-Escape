package characters;

public interface Fighter {
  String getName();

  int getHealth();

  int getAttack();

  boolean isAlive();

  void takeDamage(int amount);
}
