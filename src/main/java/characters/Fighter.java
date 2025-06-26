package characters;

public interface Fighter {
  String getName();

  int getHealth();

  int getAttack();

  int getDefense();

  boolean isAlive();

  void takeDamage(int amount);
}
