package fr.paladium.palarpg.module.stat.server.manager.impl;

import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.impl.StatBonusLootCapability;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class BonusLootManager {
  public double getBonusLoot(EntityLivingBase entity, String type) {
    RPGStatPlayerData statEntity = (RPGStatPlayerData)RPGPlayer.getData((Entity)entity, "stats");
    if (statEntity == null)
      return 1.0D; 
    StatBonusLootCapability stat = statEntity.getCapabilitiesByClass(StatBonusLootCapability.class).stream().filter(bonusLoot -> bonusLoot.isLootType(type)).findAny().orElse(null);
    if (stat == null)
      return 1.0D; 
    return ((Number)stat.getValue()).doubleValue();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\server\manager\impl\BonusLootManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */