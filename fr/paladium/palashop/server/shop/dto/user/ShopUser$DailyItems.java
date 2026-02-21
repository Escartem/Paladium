package fr.paladium.palashop.server.shop.dto.user;

import java.util.ArrayList;
import java.util.List;

public class DailyItems {
  public List<String> items;
  
  public String toString() {
    return "ShopUser.DailyItems(items=" + getItems() + ")";
  }
  
  public DailyItems(List<String> items) {
    this.items = items;
  }
  
  public List<String> getItems() {
    return this.items;
  }
  
  public DailyItems() {
    this.items = new ArrayList<>();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\dt\\user\ShopUser$DailyItems.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */