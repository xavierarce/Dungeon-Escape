package inventory;

import characters.player.Player;
import input.InputProvider;

public class LootHandler {

  public static void handle(Item loot, Player player, InputProvider inputProvider) {
    System.out.println("🎁 You found: " + loot.getName());

    if (loot instanceof Weapon newWeapon) {
      System.out.println("Current weapon: " + player.getEquippedWeaponName());
      System.out.println("New weapon: " + newWeapon.getName() + " (+" + newWeapon.getAttackBoost() + " ATK)");
      if (askYesNo("Equip this weapon? (y/n): ", inputProvider)) {
        player.equipWeapon(newWeapon);
      }
    } else if (loot instanceof Armor newArmor) {
      System.out.println("Current armor: " + player.getEquippedArmorName());
      System.out.println("New armor: " + newArmor.getName() + " (+" + newArmor.getDefenseBoost() + " DEF)");
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
