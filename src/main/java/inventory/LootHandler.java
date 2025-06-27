package inventory;

import characters.player.Player;
import input.InputProvider;

public class LootHandler {

  public static void handle(Item loot, Player player, InputProvider inputProvider) {
    System.out.println("🎁 You found: " + loot.getName());

    if (loot instanceof Weapon newWeapon) {
      Weapon currentWeapon = player.getEquippedWeapon();
      int currentAtk = currentWeapon != null ? currentWeapon.getAttackBoost() : 0;

      System.out.println("Current weapon: " + (currentWeapon != null ? currentWeapon.getName() : "None") +
          " (+" + currentAtk + " ATK)");
      System.out.println("New weapon: " + newWeapon.getName() + " (+" + newWeapon.getAttackBoost() + " ATK)");

      int diff = newWeapon.getAttackBoost() - currentAtk;
      System.out.println("⚔️ ATK Change: " + (diff >= 0 ? "+" : "") + diff);

      if (askYesNo("Equip this weapon? (y/n): ", inputProvider)) {
        player.equipWeapon(newWeapon);
      }
    } else if (loot instanceof Armor newArmor) {
      Armor currentArmor = player.getEquippedArmor();
      int currentDef = currentArmor != null ? currentArmor.getDefenseBoost() : 0;

      System.out.println("Current armor: " + (currentArmor != null ? currentArmor.getName() : "None") +
          " (+" + currentDef + " DEF)");
      System.out.println("New armor: " + newArmor.getName() + " (+" + newArmor.getDefenseBoost() + " DEF)");

      int diff = newArmor.getDefenseBoost() - currentDef;
      System.out.println("🛡️ DEF Change: " + (diff >= 0 ? "+" : "") + diff);

      if (askYesNo("Equip this armor? (y/n): ", inputProvider)) {
        player.equipArmor(newArmor);
      }
    }

    player.getInventory().addItem(loot);
  }

  private static boolean askYesNo(String prompt, InputProvider inputProvider) {
    while (true) {
      System.out.print(prompt);
      String input = inputProvider.nextLine().trim().toLowerCase();
      if (input.equals("y"))
        return true;
      if (input.equals("n"))
        return false;
      System.out.println("Please enter 'y' or 'n'.");
    }
  }
}
