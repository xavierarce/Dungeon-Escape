package inventory;

import characters.player.Player;
import input.InputProvider;

public class LootHandler {

  public static void handle(Item loot, Player player, InputProvider inputProvider) {
    System.out.println("🎁 You found: " + loot.getName());

    String type = (loot instanceof Weapon) ? "weapon"
        : (loot instanceof Armor) ? "armor"
            : "item";

    while (true) {
      System.out.println("\nWhat would you like to do?");
      System.out.println("1. Equip " + type);
      System.out.println("2. Regenerate 30 HP");
      System.out.println("3. Skip");

      System.out.print("Choose: ");
      String input = inputProvider.nextLine().trim();

      switch (input) {
        case "1":
          if (loot instanceof Weapon newWeapon) {
            System.out.println("Current weapon: " + player.getEquippedWeaponName());
            System.out.println("New weapon: " + newWeapon.getName() + " (+" + newWeapon.getAttackBoost() + " ATK)");
            player.equipWeapon(newWeapon);
          } else if (loot instanceof Armor newArmor) {
            System.out.println("Current armor: " + player.getEquippedArmorName());
            System.out.println("New armor: " + newArmor.getName() + " (+" + newArmor.getDefenseBoost() + " DEF)");
            player.equipArmor(newArmor);
          } else {
            System.out.println("❌ This item cannot be equipped.");
          }
          player.getInventory().addItem(loot);
          return;

        case "2":
          int healed = Math.min(30, player.getMaxHealth() - player.getHealth());
          player.setHealth(player.getHealth() + healed);
          System.out.println("❤️ You regenerated " + healed + " HP.");
          return;

        case "3":
          System.out.println("You chose to skip the item.");
          player.getInventory().addItem(loot);
          return;

        default:
          System.out.println("❌ Invalid input. Please select 1, 2, or 3.");
      }
    }
  }

}
