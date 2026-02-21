package fr.paladium.palashop.provider.item.dto;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palashop.common.provider.dto.IShopProvider;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.item.ShopItem;
import java.util.Map;
import lombok.NonNull;
import net.minecraft.item.ItemStack;

public class ItemShopItem extends ShopItem {
  private final String item;
  
  private transient ItemStack itemStack;
  
  private transient boolean built;
  
  public String toString() {
    return "ItemShopItem(item=" + getItem() + ", itemStack=" + getItemStack() + ", built=" + isBuilt() + ")";
  }
  
  public String getItem() {
    return this.item;
  }
  
  public boolean isBuilt() {
    return this.built;
  }
  
  public ItemShopItem(IShopProvider<? extends IShopItem> providerInstance, String parent, String template, String id, String provider, String name, String thumbnail, String description, ShopRarity rarity, Long price, Float discount, Long subscription, String[] conditions, Boolean uniquePurchase, Boolean available, Boolean buyable, ShopItem.ExecutionType executionType, String[] executions, Integer dailyWeight, Map<String, Object> additionalData, String item) {
    super(providerInstance, parent, template, id, provider, name, thumbnail, description, rarity, price, discount, subscription, conditions, uniquePurchase, available, buyable, executionType, executions, dailyWeight, additionalData);
    this.item = item;
  }
  
  @NonNull
  public String getName() {
    if (getItemStack() != null)
      return "§r" + getItemStack().func_82833_r() + (((getItemStack()).field_77994_a <= 1) ? "" : (" §rx" + (getItemStack()).field_77994_a)); 
    return super.getName();
  }
  
  public ItemStack getItemStack() {
    if (this.built)
      return this.itemStack; 
    return build();
  }
  
  private ItemStack build() {
    this.built = true;
    return this.itemStack = ItemUtils.parse(this.item);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\item\dto\ItemShopItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */