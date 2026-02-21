package fr.paladium.palarpg.module.equipment.common.loader.cache;

import fr.paladium.palarpg.module.equipment.EquipmentModule;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemType;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGItemData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RPGItemsCache {
  private static final Map<RPGItemType, Map<String, IRPGItem>> ITEMS_CACHE = new HashMap<>();
  
  public static Map<RPGItemType, Map<String, IRPGItem>> raw() {
    return ITEMS_CACHE;
  }
  
  public static void set(Map<RPGItemType, Map<String, IRPGItem>> items) {
    ITEMS_CACHE.clear();
    ITEMS_CACHE.putAll(items);
  }
  
  public static void clear() {
    ITEMS_CACHE.clear();
  }
  
  public static List<IRPGItem> getAllItems() {
    List<IRPGItem> list = new ArrayList<>();
    for (Map<String, IRPGItem> items : ITEMS_CACHE.values())
      list.addAll(items.values()); 
    return list;
  }
  
  public static List<IRPGItem> getItems(RPGItemType type) {
    return new ArrayList<>(((Map)ITEMS_CACHE.getOrDefault(type, new HashMap<>())).values());
  }
  
  public static <T extends IRPGItem> Optional<T> getItem(RPGItemType type, String id) {
    if (!ITEMS_CACHE.containsKey(type))
      return Optional.empty(); 
    IRPGItem data = (IRPGItem)((Map)ITEMS_CACHE.get(type)).get(id);
    if (data == null)
      return Optional.empty(); 
    return Optional.of((T)data);
  }
  
  public static <T extends IRPGItem> Optional<T> getItem(RPGItemType type, int hashcode) {
    if (!ITEMS_CACHE.containsKey(type))
      return Optional.empty(); 
    for (IRPGItem data : ((Map)ITEMS_CACHE.get(type)).values()) {
      if (data.hashCode() == hashcode)
        return Optional.of((T)data); 
    } 
    return Optional.empty();
  }
  
  public static void addItem(IRPGItem item) {
    EquipmentModule.logger.info("[+] " + item.getId() + " loaded", new Object[0]);
    ((Map<String, IRPGItem>)ITEMS_CACHE.computeIfAbsent(item.getItemData().getType(), key -> new HashMap<>())).put(item.getId(), item);
  }
  
  public static void removeItem(RPGItemData data) {
    ((Map)ITEMS_CACHE.get(data.getType())).remove(data.getId());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\loader\cache\RPGItemsCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */