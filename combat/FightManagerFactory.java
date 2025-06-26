package combat;

import characters.enemy.Enemy;
import characters.player.Player;
import actions.IActionProvider;

public interface FightManagerFactory {
  FightManager create(Player player, Enemy enemy, IActionProvider actionProvider);
}
