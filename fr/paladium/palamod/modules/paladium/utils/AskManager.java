package fr.paladium.palamod.modules.paladium.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;

public class AskManager {
  public static Map<EntityPlayer, List<Map<EntityPlayer, AskType>>> asked = new HashMap<>();
  
  public static void addAsk(EntityPlayer player, EntityPlayer target, AskType type) {
    List<Map<EntityPlayer, AskType>> map = new ArrayList<>();
    if (asked.containsKey(player))
      map = asked.get(player); 
    Map<EntityPlayer, AskType> data = new HashMap<>();
    data.put(target, type);
    if (!map.contains(data))
      map.add(data); 
    asked.put(player, map);
  }
  
  public static EntityPlayer getAsker(EntityPlayer player, AskType type) {
    for (EntityPlayer pl : asked.keySet()) {
      Map<EntityPlayer, AskType> data = new HashMap<>();
      data.put(player, type);
      if (((List)asked.get(pl)).contains(data))
        return pl; 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladiu\\utils\AskManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */