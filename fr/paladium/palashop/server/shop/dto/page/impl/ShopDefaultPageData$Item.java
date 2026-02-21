package fr.paladium.palashop.server.shop.dto.page.impl;

import fr.paladium.palashop.server.shop.dto.ConditionalBuyableObject;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import java.util.List;

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


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\dto\page\impl\ShopDefaultPageData$Item.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */