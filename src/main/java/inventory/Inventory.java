package inventory;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
  private List<Item> items = new ArrayList<>();

  public void addItem(Item item) {
    items.add(item);
    System.out.println("Obtained item: " + item.getName());
  }

  public List<Item> getItems() {
    return items;
  }
}
