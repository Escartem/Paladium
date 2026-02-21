package fr.paladium.palashop.server.shop.dto.page.impl;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palashop.server.shop.ShopManager;
import fr.paladium.palashop.server.shop.condition.ShopConditions;
import fr.paladium.palashop.server.shop.condition.exception.ShopConditionException;
import fr.paladium.palashop.server.shop.dto.ConditionalBuyableObject;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.page.ShopPageData;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;

public class ShopDefaultPageData implements ShopPageData {
  private List<Item> items;
  
  public List<Item> getItems() {
    return this.items;
  }
  
  @SideOnly(Side.SERVER)
  public void create(@NonNull ShopUser user, @NonNull EntityPlayer player) {
    if (user == null)
      throw new NullPointerException("user is marked non-null but is null"); 
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (this.items == null || this.items.isEmpty())
      return; 
    for (Item item : this.items) {
      item.conditionalItems = new ArrayList();
      if (item.getConditions() != null && !item.getConditions().isEmpty()) {
        boolean valid = true;
        for (String condition : item.getConditions()) {
          ShopConditionException exception = ShopConditions.validate(user, player, condition, false);
          if (exception != null) {
            valid = false;
            break;
          } 
        } 
        if (!valid)
          continue; 
      } 
      for (IShopItem shopItem : item.items) {
        if (shopItem == null)
          continue; 
        Optional<IShopItem> optional = ShopManager.getItem(shopItem.getUniqueId());
        if (!optional.isPresent() || !((IShopItem)optional.get()).isAvailable().booleanValue())
          continue; 
        item.conditionalItems.add(ConditionalBuyableObject.from(user, player, optional.get()));
      } 
    } 
  }
  
  public class Item {
    private String name;
    
    private String description;
    
    private List<IShopItem> items;
    
    private List<ConditionalBuyableObject<IShopItem>> conditionalItems;
    
    private List<String> conditions;
    
    public String getName() {
      return this.name;
    }
    
    public String getDescription() {
      return this.description;
    }
    
    public List<IShopItem> getItems() {
      return this.items;
    }
    
    public List<ConditionalBuyableObject<IShopItem>> getConditionalItems() {
      return this.conditionalItems;
    }
    
    public List<String> getConditions() {
      return this.conditions;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\dto\page\impl\ShopDefaultPageData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */