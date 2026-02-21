package fr.paladium.palaforgeutils.lib.cooldown;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class CooldownUtils {
  private static final Map<String, Map<UUID, Long>> COOLDOWN_MAP = new HashMap<>();
  
  public static void initCooldown(Entity player, String key) {
    initCooldown(player.func_110124_au(), key);
  }
  
  public static void initCooldown(UUID uniqueId, String key) {
    if (!COOLDOWN_MAP.containsKey(key))
      COOLDOWN_MAP.put(key, new HashMap<>()); 
    ((Map<UUID, Long>)COOLDOWN_MAP.get(key)).put(uniqueId, Long.valueOf(0L));
  }
  
  public static void setCooldown(Entity player, String key, long time) {
    setCooldown(player.func_110124_au(), key, time);
  }
  
  public static void setCooldown(UUID uniqueId, String key, long time) {
    if (!COOLDOWN_MAP.containsKey(key))
      initCooldown(uniqueId, key); 
    ((Map<UUID, Long>)COOLDOWN_MAP.get(key)).put(uniqueId, Long.valueOf(System.currentTimeMillis() + time));
  }
  
  public static void removeCooldown(Entity player, String key) {
    removeCooldown(player.func_110124_au(), key);
  }
  
  public static void removeCooldown(UUID uniqueId, String key) {
    if (!COOLDOWN_MAP.containsKey(key))
      return; 
    ((Map)COOLDOWN_MAP.get(key)).remove(uniqueId);
  }
  
  public static boolean hasCooldown(EntityPlayer player, String key) {
    return hasCooldown(player.func_110124_au(), key);
  }
  
  public static boolean hasCooldown(UUID uniqueId, String key) {
    if (!COOLDOWN_MAP.containsKey(key))
      return false; 
    return ((Map)COOLDOWN_MAP.get(key)).containsKey(uniqueId);
  }
  
  public static boolean isOnCooldown(Entity entity, String key) {
    return isOnCooldown(entity.func_110124_au(), key);
  }
  
  public static boolean isOnCooldown(UUID uniqueId, String key) {
    return (getCooldown(uniqueId, key) > System.currentTimeMillis());
  }
  
  public static long getCooldown(Entity entity, String key) {
    return getCooldown(entity.func_110124_au(), key);
  }
  
  public static long getCooldown(UUID uniqueId, String key) {
    if (!hasCooldown(uniqueId, key))
      return 0L; 
    return ((Long)((Map)COOLDOWN_MAP.get(key)).getOrDefault(uniqueId, Long.valueOf(0L))).longValue();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\cooldown\CooldownUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */