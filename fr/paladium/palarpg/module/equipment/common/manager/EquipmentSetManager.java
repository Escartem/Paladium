package fr.paladium.palarpg.module.equipment.common.manager;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palaforgeutils.lib.server.ServerType;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.equipment.common.event.PlayerEquipmentSetChangedEvent;
import fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemArmor;
import fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemSword;
import fr.paladium.palarpg.module.equipment.common.loader.cache.RPGEquipmentSetCache;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGEquipmentSetData;
import fr.paladium.palarpg.module.equipment.common.loader.data.impl.RPGSwordItemData;
import fr.paladium.palarpg.module.equipment.common.loader.util.ItemStatMutator;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.AStatCapability;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatCapabilityMutator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class EquipmentSetManager {
  public static final String MUTATOR_PREFIX = "set_bonus";
  
  private static final Cache<String, Map<String, Integer>> PLAYER_SET_CACHE = CacheBuilder.newBuilder().build();
  
  public static void clear(EntityPlayer player) {
    clear(UUIDUtils.toString((Entity)player));
  }
  
  public static void clear(UUID playerUUID) {
    clear(UUIDUtils.toString(playerUUID));
  }
  
  public static void clear(String playerUUID) {
    PLAYER_SET_CACHE.invalidate(playerUUID);
  }
  
  public static void checkPlayerSet(EntityPlayer player) throws ExecutionException {
    String playerUUID = UUIDUtils.toString((Entity)player);
    Map<String, Integer> previousSet = (Map<String, Integer>)PLAYER_SET_CACHE.get(playerUUID, HashMap::new);
    Map<String, Integer> setMap = getAllSet(player);
    PLAYER_SET_CACHE.put(playerUUID, setMap);
    if (!previousSet.equals(setMap)) {
      Map<String, Integer> newSet = (Map<String, Integer>)PLAYER_SET_CACHE.get(playerUUID, HashMap::new);
      if (!previousSet.isEmpty() && FMLCommonHandler.instance().getSide() == Side.SERVER && Server.is(new ServerType[] { ServerType.RPG }))
        previousSet.keySet().forEach(setName -> removeSetBonuses(player, setName)); 
      if (!newSet.isEmpty() && FMLCommonHandler.instance().getSide() == Side.SERVER && Server.is(new ServerType[] { ServerType.RPG }))
        newSet.forEach((setId, count) -> applySetBonus(player, setId, count.intValue())); 
      MinecraftForge.EVENT_BUS.post((Event)new PlayerEquipmentSetChangedEvent(player, previousSet, setMap));
      RPGStatPlayerData statPlayerData = (RPGStatPlayerData)RPGPlayer.getData((Entity)player, "stats");
      if (statPlayerData == null)
        return; 
      statPlayerData.applyAndSync();
    } 
  }
  
  public static int getSetPieceCount(EntityPlayer player, String setId) {
    try {
      return ((Integer)((Map)PLAYER_SET_CACHE.get(UUIDUtils.toString((Entity)player), HashMap::new)).getOrDefault(setId, Integer.valueOf(0))).intValue();
    } catch (ExecutionException ignored) {
      return 0;
    } 
  }
  
  private static Map<String, Integer> getAllSet(EntityPlayer player) {
    Map<String, Integer> setCount = new HashMap<>();
    if (player.func_71045_bC() != null && player.func_71045_bC().func_77973_b() instanceof RPGItemSword) {
      RPGItemSword rpgSword = (RPGItemSword)player.func_71045_bC().func_77973_b();
      RPGSwordItemData swordData = rpgSword.getItemData();
      if (swordData.getSet() != null && !swordData.getSet().trim().isEmpty())
        setCount.put(swordData.getSet(), Integer.valueOf(((Integer)setCount.getOrDefault(swordData.getSet(), Integer.valueOf(0))).intValue() + 1)); 
    } 
    for (int i = 0; i < player.field_71071_by.field_70460_b.length; i++) {
      ItemStack item = player.field_71071_by.field_70460_b[i];
      if (item != null && item.func_77973_b() instanceof RPGItemArmor) {
        RPGItemArmor armor = (RPGItemArmor)item.func_77973_b();
        String setId = armor.getArmor().getSet();
        if (setId != null && !setId.trim().isEmpty())
          setCount.put(setId, Integer.valueOf(((Integer)setCount.getOrDefault(setId, Integer.valueOf(0))).intValue() + 1)); 
      } 
    } 
    return setCount;
  }
  
  public static void applySetBonus(EntityPlayer player, String setId, int count) {
    RPGStatPlayerData statPlayerData = (RPGStatPlayerData)RPGPlayer.getData((Entity)player, "stats");
    if (statPlayerData == null)
      return; 
    Optional<RPGEquipmentSetData> equipSet = RPGEquipmentSetCache.getSet(setId);
    if (!equipSet.isPresent())
      return; 
    RPGEquipmentSetData setData = equipSet.get();
    setData.getMutators().forEach((req, mutators) -> {
          if (count >= req.intValue())
            mutators.forEach(()); 
        });
    statPlayerData.applyAndSync();
  }
  
  public static void removeAllSetBonuses(EntityPlayer player) {
    RPGStatPlayerData statPlayerData = (RPGStatPlayerData)RPGPlayer.getData((Entity)player, "stats");
    if (statPlayerData == null)
      return; 
    statPlayerData.getCapabilities().forEach(capability -> {
          capability.getMutators().removeIf(());
          capability.computeValue();
        });
    statPlayerData.applyAndSync();
  }
  
  public static void removeSetBonuses(EntityPlayer player, String setId) {
    RPGStatPlayerData statPlayerData = (RPGStatPlayerData)RPGPlayer.getData((Entity)player, "stats");
    Optional<RPGEquipmentSetData> optEquipSet = RPGEquipmentSetCache.getSet(setId);
    if (statPlayerData == null || !optEquipSet.isPresent())
      return; 
    ((RPGEquipmentSetData)optEquipSet.get()).getMutators().forEach((req, mutators) -> {
          if (mutators.isEmpty())
            return; 
          mutators.forEach(());
        });
    statPlayerData.applyAndSync();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\manager\EquipmentSetManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */