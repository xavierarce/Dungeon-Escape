package combat;

import actions.IActionProvider;
import characters.enemy.Enemy;
import characters.player.Player;
import input.InputProvider;

public class DefaultFightManagerFactory implements FightManagerFactory {
  @Override
  public FightManager create(Player player, Enemy enemy, IActionProvider actionProvider, InputProvider inputProvider) {
    return new FightManager(player, enemy, actionProvider, inputProvider);
  }
}
