package fr.paladium.palaclicker.server.config.shop;

import fr.paladium.palaclicker.server.config.shop.dto.ClickerShopItem;
import fr.paladium.palaclicker.server.config.shop.dto.ClickerShopType;
import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@ConfigFile(path = "clicker/shop.json", blocking = true)
public class ShopGlobalConfig implements IConfig {
  public List<ClickerShopItem> getItemList() {
    return this.itemList;
  }
  
  private final List<ClickerShopItem> itemList = new ArrayList<>();
  
  public boolean isValid() {
    return (this.itemList != null && !this.itemList.isEmpty());
  }
  
  public void onLoaded() {
    this.itemList.forEach(ClickerShopItem::make);
  }
  
  public void onReloaded() {
    onLoaded();
  }
  
  public void onFailed() {}
  
  public ClickerShopItem getRandomItem(ClickerShopType type, Map<String, Integer> buildings) {
    List<ClickerShopItem> items = getItems(type);
    if (items.isEmpty())
      return null; 
    Collections.shuffle(items);
    int totalWeight = 0;
    for (ClickerShopItem item : items) {
      boolean valid = true;
      for (Map.Entry<String, Integer> building : buildings.entrySet()) {
        String buildingId = building.getKey();
        int buildingLevel = ((Integer)building.getValue()).intValue();
        if (item.isForbidden(buildingId, buildingLevel)) {
          valid = false;
          break;
        } 
      } 
      if (item.getRequirement() != null)
        for (Map.Entry<String, Integer> requirement : (Iterable<Map.Entry<String, Integer>>)item.getRequirement().entrySet()) {
          String buildingId = requirement.getKey();
          int buildingLevel = ((Integer)requirement.getValue()).intValue();
          if (!buildings.containsKey(buildingId) || ((Integer)buildings.get(buildingId)).intValue() < buildingLevel) {
            valid = false;
            break;
          } 
        }  
      if (!valid)
        continue; 
      totalWeight += item.getWeight();
    } 
    int randomValue = (new Random()).nextInt(totalWeight);
    int currentWeight = 0;
    for (ClickerShopItem item : items) {
      boolean valid = true;
      for (Map.Entry<String, Integer> building : buildings.entrySet()) {
        String buildingId = building.getKey();
        int buildingLevel = ((Integer)building.getValue()).intValue();
        if (item.isForbidden(buildingId, buildingLevel)) {
          valid = false;
          break;
        } 
      } 
      if (item.getRequirement() != null)
        for (Map.Entry<String, Integer> requirement : (Iterable<Map.Entry<String, Integer>>)item.getRequirement().entrySet()) {
          String buildingId = requirement.getKey();
          int buildingLevel = ((Integer)requirement.getValue()).intValue();
          if (!buildings.containsKey(buildingId) || ((Integer)buildings.get(buildingId)).intValue() < buildingLevel) {
            valid = false;
            break;
          } 
        }  
      if (!valid)
        continue; 
      currentWeight += item.getWeight();
      if (randomValue <= currentWeight)
        return item; 
    } 
    return null;
  }
  
  public List<ClickerShopItem> getRandomItem(ClickerShopType type, int size, Map<String, Integer> buildings) {
    List<ClickerShopItem> randomItems = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      ClickerShopItem randomItem;
      do {
        randomItem = getRandomItem(type, buildings);
      } while (randomItem == null || randomItems.contains(randomItem));
      randomItems.add(randomItem);
    } 
    return randomItems;
  }
  
  public List<ClickerShopItem> getItems(ClickerShopType type) {
    return (List<ClickerShopItem>)this.itemList.stream().filter(item -> item.getType().equals(type)).collect(Collectors.toList());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\server\config\shop\ShopGlobalConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */