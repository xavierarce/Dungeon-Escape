package actions;

import characters.Fighter;

public interface ICombatAction {
  /**
   * Executes the action.
   * 
   * @param player the player executing the action
   * @param enemy  the opponent fighter
   * @return a message describing the result of the action
   */
  String execute(Fighter player, Fighter enemy);
}
