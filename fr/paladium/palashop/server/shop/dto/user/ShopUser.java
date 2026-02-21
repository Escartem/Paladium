package fr.paladium.palashop.server.shop.dto.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.NonNull;
import org.bson.Document;

public class ShopUser {
  public String uuid;
  
  public Set<String> ownedItems;
  
  public DailyItems dailyItems;
  
  public Map<String, List<String>> itemMap;
  
  public Map<String, Document> dataMap;
  
  public String toString() {
    return "ShopUser(uuid=" + getUuid() + ", ownedItems=" + getOwnedItems() + ", dailyItems=" + getDailyItems() + ", itemMap=" + getItemMap() + ", dataMap=" + getDataMap() + ")";
  }
  
  public ShopUser() {}
  
  public ShopUser(String uuid, Set<String> ownedItems, DailyItems dailyItems, Map<String, List<String>> itemMap, Map<String, Document> dataMap) {
    this.uuid = uuid;
    this.ownedItems = ownedItems;
    this.dailyItems = dailyItems;
    this.itemMap = itemMap;
    this.dataMap = dataMap;
  }
  
  public String getUuid() {
    return this.uuid;
  }
  
  public Set<String> getOwnedItems() {
    return this.ownedItems;
  }
  
  public DailyItems getDailyItems() {
    return this.dailyItems;
  }
  
  public Map<String, List<String>> getItemMap() {
    return this.itemMap;
  }
  
  public Map<String, Document> getDataMap() {
    return this.dataMap;
  }
  
  public ShopUser(@NonNull String uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    this.uuid = uuid;
    this.ownedItems = new HashSet<>();
    this.dailyItems = new DailyItems();
    this.itemMap = new HashMap<>();
    this.dataMap = new HashMap<>();
  }
  
  public boolean hasData(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return this.dataMap.containsKey(id);
  }
  
  public Document getData(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return this.dataMap.get(id);
  }
  
  public static class DailyItems {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\dt\\user\ShopUser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */