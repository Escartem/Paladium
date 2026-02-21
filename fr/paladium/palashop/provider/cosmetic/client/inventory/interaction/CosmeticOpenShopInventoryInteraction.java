package fr.paladium.palashop.provider.cosmetic.client.inventory.interaction;

import fr.paladium.palashop.client.ui.impl.inventory.manager.dto.IShopInventoryElement;
import fr.paladium.palashop.client.ui.impl.inventory.manager.dto.IShopInventoryInteraction;
import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.UIShopCosmetic;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import lombok.NonNull;

public class CosmeticOpenShopInventoryInteraction implements IShopInventoryInteraction {
  public String getName() {
    return "examiner";
  }
  
  public boolean isValid(@NonNull IShopInventoryElement element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    return element instanceof fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
  }
  
  public boolean isEnabled(@NonNull IShopInventoryElement element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    return true;
  }
  
  public void execute(@NonNull IShopInventoryElement element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    ZUI.open((UI)new UIShopCosmetic());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\client\inventory\interaction\CosmeticOpenShopInventoryInteraction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */