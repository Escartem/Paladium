package fr.paladium.palashop.client.ui.impl.inventory.manager;

import fr.paladium.palashop.client.ui.impl.inventory.manager.dto.IShopInventoryInteraction;
import fr.paladium.palashop.client.ui.impl.inventory.manager.dto.ShopInventoryHolder;
import fr.paladium.zephyrui.lib.utils.list.IndexedElement;
import fr.paladium.zephyrui.lib.utils.list.IndexedLinkedList;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;

public class ShopInventoryManager {
  private static final IndexedLinkedList<ShopInventoryHolder> HOLDER_LIST = new IndexedLinkedList();
  
  private static final List<IShopInventoryInteraction> INTERACTION_LIST = new ArrayList<>();
  
  public static void registerHolder(@NonNull ShopInventoryHolder holder) {
    if (holder == null)
      throw new NullPointerException("holder is marked non-null but is null"); 
    HOLDER_LIST.add((IndexedElement)holder);
  }
  
  public static void registerInteraction(@NonNull IShopInventoryInteraction interaction) {
    if (interaction == null)
      throw new NullPointerException("interaction is marked non-null but is null"); 
    INTERACTION_LIST.add(interaction);
  }
  
  public static ShopInventoryHolder getHolder(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return HOLDER_LIST.ordered().stream().filter(page -> page.getId().equals(id)).findFirst().orElse(null);
  }
  
  @NonNull
  public static IndexedLinkedList<ShopInventoryHolder> getHolderList() {
    return HOLDER_LIST;
  }
  
  @NonNull
  public static List<IShopInventoryInteraction> getInteractionList() {
    return INTERACTION_LIST;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\inventory\manager\ShopInventoryManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */