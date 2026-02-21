package fr.paladium.palarpg.module.equipment.common.loader.cache;

import fr.paladium.palarpg.module.equipment.EquipmentModule;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGEquipmentSetData;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RPGEquipmentSetCache {
  private static final Map<String, RPGEquipmentSetData> SET_CACHE = new HashMap<>();
  
  public static Optional<RPGEquipmentSetData> getSet(String id) {
    if (id == null)
      return Optional.empty(); 
    return Optional.ofNullable(SET_CACHE.get(id));
  }
  
  public static void addSet(RPGEquipmentSetData set) {
    EquipmentModule.logger.info("[+] " + set.getId() + " loaded", new Object[0]);
    SET_CACHE.put(set.getId(), set);
  }
  
  public static void removeSet(RPGEquipmentSetData set) {
    SET_CACHE.remove(set.getId());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\loader\cache\RPGEquipmentSetCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */