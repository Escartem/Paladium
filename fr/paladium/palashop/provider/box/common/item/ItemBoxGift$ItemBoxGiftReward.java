package fr.paladium.palashop.provider.box.common.item;

import fr.paladium.palashop.server.shop.dto.ShopRarity;
import net.minecraft.item.ItemStack;

public class ItemBoxGiftReward {
  private final ItemStack item;
  
  private final ShopRarity rarity;
  
  public ItemBoxGiftReward(ItemStack item, ShopRarity rarity) {
    this.item = item;
    this.rarity = rarity;
  }
  
  public ItemStack getItem() {
    return this.item;
  }
  
  public ShopRarity getRarity() {
    return this.rarity;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\item\ItemBoxGift$ItemBoxGiftReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */