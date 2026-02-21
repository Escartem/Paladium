package fr.paladium.palarpg.module.stat.server.manager.impl;

import fr.paladium.palaforgeutils.lib.cooldown.CooldownUtils;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatMutationType;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.impl.StatNumberCapability;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class HealthManager {
  public void heal(EntityLivingBase entity, float amount, HealSource source) {
    entity.func_70606_j(entity.func_110143_aJ() + amount);
  }
  
  public void applyNaturalRegeneration(EntityLivingBase entity) {
    RPGStatPlayerData statEntity = (RPGStatPlayerData)RPGPlayer.getData((Entity)entity, "stats");
    if (statEntity == null)
      return; 
    if (entity instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)entity;
      if (CooldownUtils.isOnCooldown((Entity)player, "PALARPG:COMBAT"))
        return; 
      if (CooldownUtils.hasCooldown((EntityPlayer)player, "PALARPG:COMBAT") && !CooldownUtils.isOnCooldown((Entity)player, "PALARPG:COMBAT"))
        CooldownUtils.removeCooldown((Entity)player, "PALARPG:COMBAT"); 
    } 
    float regen = ((Number)statEntity.getHealthRegeneration().getValue()).floatValue();
    StatNumberCapability statNumberCapability = statEntity.getHealthRegenerationSpeed();
    float regenSpeed = ((Number)statNumberCapability.getDefaultValue()).floatValue() - ((Number)statNumberCapability.getTotalValue(StatMutationType.ADDITIVE)).floatValue();
    float regenSpeedMult = ((Number)statNumberCapability.getTotalValue(StatMutationType.MULTIPLICATIVE)).floatValue();
    if (regenSpeedMult > 0.0F)
      regenSpeed -= regenSpeed * regenSpeedMult; 
    regenSpeed = Math.min(Math.max(regenSpeed, 1.0F), 5.0F);
    if (entity.func_110143_aJ() > 0.0F && entity.func_110143_aJ() < entity.func_110138_aP() && entity.field_70170_p.func_82736_K().func_82766_b("naturalRegeneration") && entity.field_70173_aa % 20.0F * regenSpeed == 0.0F)
      heal(entity, regen, HealSource.NATURAL_REGENERATION); 
  }
  
  public enum HealSource {
    UNKNOWN, EFFECT, NATURAL_REGENERATION;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\server\manager\impl\HealthManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */