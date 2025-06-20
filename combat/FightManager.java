package combat;

import actions.IActionProvider;
import actions.ICombatAction;
import characters.Fighter;

public class FightManager {
  private Fighter player;
  private Fighter enemy;
  private IActionProvider actionProvider;

  public FightManager(Fighter player, Fighter enemy, IActionProvider actionProvider) {
    this.player = player;
    this.enemy = enemy;
    this.actionProvider = actionProvider;
  }

  public boolean start() {
    System.out.println("⚔️ A wild " + enemy.getName() + " appears!");
    while (player.isAlive() && enemy.isAlive()) {
      showStatus();

      ICombatAction playerAction = actionProvider.getAction();
      String result = playerAction.execute(player, enemy);
      System.out.println(result);

      if (!enemy.isAlive()) {
        System.out.println("🎉 You defeated the " + enemy.getName() + "!");
        return true;
      }

      enemyAttack();
    }
    return false;
  }

  private void showStatus() {
    System.out.println("\nYour HP: " + player.getHealth() + " | Enemy HP: " + enemy.getHealth());
  }

  private void enemyAttack() {
    System.out.println(enemy.getName() + " attacks!");
    player.takeDamage(enemy.getAttack());
  }
}
