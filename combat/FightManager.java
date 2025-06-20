package combat;

import actions.IActionProvider;
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
      int action = actionProvider.getAction();

      if (action == 1) {
        playerAttack();
        if (!enemy.isAlive()) {
          System.out.println("🎉 You defeated the " + enemy.getName() + "!");
          return true;
        }
        enemyAttack();
      }
    }
    return false;
  }

  private void showStatus() {
    System.out.println("\nYour HP: " + player.getHealth() + " | Enemy HP: " + enemy.getHealth());
  }

  private void playerAttack() {
    enemy.takeDamage(player.getAttack());
  }

  private void enemyAttack() {
    System.out.println(enemy.getName() + " attacks!");
    player.takeDamage(enemy.getAttack());
  }
}
