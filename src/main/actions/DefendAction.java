package actions;

import characters.Fighter;

public class DefendAction implements ICombatAction {
  @Override
  public String execute(Fighter player, Fighter enemy) {
    // Example: reduce incoming damage this turn or increase defense temporarily
    // For simplicity, let's say defend just prints a message and reduces damage
    // next attack
    // You'd add some status or flag in the Fighter class in a real design
    return player.getName() + " defends and braces for the next attack!";
  }
}
