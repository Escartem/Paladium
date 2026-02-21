package fr.paladium.palashop.provider.cosmetic.client.inventory.interaction;

import fr.paladium.palashop.client.ui.impl.inventory.manager.dto.IShopInventoryElement;
import fr.paladium.palashop.client.ui.impl.inventory.manager.dto.IShopInventoryInteraction;
import fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient;
import fr.paladium.palashop.provider.cosmetic.common.data.CosmeticPlayer;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.common.network.CSPacketUnEquipCosmetic;
import lombok.NonNull;

public class CosmeticUnEquipShopInventoryInteraction implements IShopInventoryInteraction {
  public String getName() {
    return "déséquiper";
  }
  
  public boolean isValid(@NonNull IShopInventoryElement element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    if (!(element instanceof ICosmeticClient))
      return false; 
    ICosmeticClient cosmetic = (ICosmeticClient)element;
    CosmeticPlayer player = CosmeticPlayer.me();
    return (player.getOutfit().get(cosmetic.getFactory().getId()).isPresent() && ((CosmeticPlayer.EquippedCosmetic)player.getOutfit().get(cosmetic.getFactory().getId()).get()).has((ICosmetic)cosmetic));
  }
  
  public boolean isEnabled(@NonNull IShopInventoryElement element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    return (CosmeticPlayer.me() != null);
  }
  
  public void execute(@NonNull IShopInventoryElement element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    ICosmeticClient cosmetic = (ICosmeticClient)element;
    (new CSPacketUnEquipCosmetic(cosmetic.getFactory().getId(), cosmetic.getId(), -1)).send();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\client\inventory\interaction\CosmeticUnEquipShopInventoryInteraction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */