package fr.paladium.pet.common.store.provider.server.dto;

import fr.paladium.palashop.common.provider.dto.IShopProvider;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.item.ShopItem;
import java.util.Map;

public class PetSkinShopItem extends ShopItem {
  private final String petId;
  
  public String toString() {
    return "PetSkinShopItem(petId=" + getPetId() + ")";
  }
  
  public String getPetId() {
    return this.petId;
  }
  
  public PetSkinShopItem(IShopProvider<? extends IShopItem> providerInstance, String parent, String template, String id, String provider, String name, String thumbnail, String description, ShopRarity rarity, Long price, Long subscription, String[] conditions, Boolean uniquePurchase, Boolean available, Boolean buyable, ShopItem.ExecutionType executionType, String[] executions, Integer dailyWeight, Map<String, Object> additionalData, String petId) {
    super(providerInstance, parent, template, id, provider, name, thumbnail, description, rarity, price, subscription, conditions, uniquePurchase, available, buyable, executionType, executions, dailyWeight, additionalData);
    this.petId = petId;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\store\provider\server\dto\PetSkinShopItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */