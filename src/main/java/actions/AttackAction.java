package actions;

import characters.Fighter;

public class AttackAction implements ICombatAction {
  @Override
  public String execute(Fighter player, Fighter enemy) {
    int damage = player.getAttack();
    enemy.takeDamage(damage);
    return player.getName() + " attacks " + enemy.getName() + " for " + damage + " damage!";
  }
}
