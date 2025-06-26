package combat;

import characters.enemy.Enemy;
import characters.player.Player;
import actions.IActionProvider;

public class DefaultFightManagerFactory implements FightManagerFactory {
  @Override
  public FightManager create(Player player, Enemy enemy, IActionProvider actionProvider) {
    return new FightManager(player, enemy, actionProvider);
  }
}
