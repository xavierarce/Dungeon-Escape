package combat;

import actions.IActionProvider;
import actions.ICombatAction;

import characters.player.Player;
import characters.enemy.Enemy;
import inventory.Item;
import inventory.LootHandler;
import input.InputProvider;
import inventory.Weapon;
import inventory.Armor;

import java.util.Random;

public class FightManager {
  private Player player;
  private Enemy enemy;
  private IActionProvider actionProvider;
  private InputProvider inputProvider;

  public FightManager(Player player, Enemy enemy, IActionProvider actionProvider, InputProvider inputProvider) {
    this.player = player;
    this.enemy = enemy;
    this.actionProvider = actionProvider;
    this.inputProvider = inputProvider;
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

        player.gainEXP(enemy.getExpReward());
        player.gainGold(enemy.getGoldReward());

        Item loot = generateRandomLoot();
        LootHandler.handle(loot, player, inputProvider);

        return true;
      }

      enemyAttack();
    }

    return false;
  }

  private void showStatus() {
    System.out.println(
        "\nLevel " + player.getLevel() + " - Your HP: " + player.getHealth() + " | Enemy HP: " + enemy.getHealth());
  }

  private void enemyAttack() {
    System.out.println(enemy.getName() + " attacks!");
    player.takeDamage(enemy.getAttack());
  }

  private Item generateRandomLoot() {
    Random rand = new Random();
    int roll = rand.nextInt(2);

    if (roll == 0) {
      int atk = 5 + rand.nextInt(6); // 5-10
      return new Weapon("Sword of Power +" + atk, atk);
    } else {
      int def = 3 + rand.nextInt(5); // 3-7
      return new Armor("Shield of Resilience +" + def, def);
    }
  }
}
