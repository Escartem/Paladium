package fr.paladium.palarpg.module.stat.server.manager.impl;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.ServersideAttributeMap;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S20PacketEntityProperties;
import net.minecraft.world.WorldServer;

public class MaxHealthManager {
  private static final UUID MAX_HEALTH_UUID = UUIDUtils.from("5f229237-1976-4881-a06d-e614d2d706a5");
  
  public void applyMaxHealth(EntityLivingBase entity) {
    RPGStatPlayerData statEntity = (RPGStatPlayerData)RPGPlayer.getData((Entity)entity, "stats");
    if (statEntity == null)
      return; 
    float totalHealth = (float)((((Number)statEntity.getMaxHealth().getValue()).floatValue() - 20.0F) + ((entity.func_110148_a(SharedMonsterAttributes.field_111267_a) != null) ? entity.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() : 20.0D));
    applyMaxHealth(entity, (entity.func_110143_aJ() > totalHealth));
  }
  
  public void applyMaxHealth(EntityLivingBase entity, boolean heal) {
    RPGStatPlayerData statEntity = (RPGStatPlayerData)RPGPlayer.getData((Entity)entity, "stats");
    if (statEntity == null)
      return; 
    if (entity.func_110148_a(SharedMonsterAttributes.field_111267_a) != null) {
      IAttributeInstance maxHealth = entity.func_110148_a(SharedMonsterAttributes.field_111267_a);
      AttributeModifier modifier = maxHealth.func_111127_a(MAX_HEALTH_UUID);
      if (modifier != null)
        maxHealth.func_111124_b(modifier); 
      maxHealth.func_111121_a(new AttributeModifier(MAX_HEALTH_UUID, "palarpg_max_health", ((Number)statEntity.getMaxHealth().getValue()).doubleValue() - 20.0D, 0));
      if (heal)
        entity.func_70606_j(entity.func_110138_aP()); 
      updateMaxHealth(entity);
    } 
  }
  
  public void resetMaxHealth(EntityLivingBase entity) {
    if (entity.func_110148_a(SharedMonsterAttributes.field_111267_a) != null && entity.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111127_a(MAX_HEALTH_UUID) != null) {
      AttributeModifier modifier = entity.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111127_a(MAX_HEALTH_UUID);
      entity.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111124_b(modifier);
      entity.func_70606_j(entity.func_110138_aP());
      updateMaxHealth(entity);
    } 
  }
  
  public void updateMaxHealth(EntityLivingBase entity) {
    try {
      WorldServer world = (WorldServer)entity.field_70170_p;
      EntityTracker tracker = world.func_73039_n();
      if (tracker == null)
        return; 
      ConcurrentHashMap<Integer, Object> trackedEntityIDs = (ConcurrentHashMap<Integer, Object>)ObfuscationReflectionHelper.getPrivateValue(EntityTracker.class, tracker, new String[] { "trackedEntityIDs", "field_72794_c" });
      if (trackedEntityIDs == null || !trackedEntityIDs.containsKey(Integer.valueOf(entity.func_145782_y())))
        return; 
      EntityTrackerEntry entry = (EntityTrackerEntry)trackedEntityIDs.get(Integer.valueOf(entity.func_145782_y()));
      if (entry == null)
        return; 
      ServersideAttributeMap attributeMap = (ServersideAttributeMap)entity.func_110140_aT();
      Set<?> set = attributeMap.func_111161_b();
      if (!set.isEmpty())
        entry.func_151261_b((Packet)new S20PacketEntityProperties(entity.func_145782_y(), set)); 
      set.clear();
    } catch (Exception|NoClassDefFoundError exception) {}
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\server\manager\impl\MaxHealthManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */