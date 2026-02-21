package fr.paladium.palarpg.module.stat.server.manager.impl;

import fr.paladium.palaforgeutils.lib.sound.SoundUtils;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public final class DodgeManager {
  public boolean tryDodge(EntityLivingBase target) {
    RPGStatPlayerData targetStats = (RPGStatPlayerData)RPGPlayer.getData((Entity)target, "stats");
    if (targetStats == null)
      return false; 
    if (target.field_70170_p.field_73012_v.nextDouble() <= ((Number)targetStats.getDodge().getValue()).doubleValue()) {
      target.field_70170_p.func_72908_a(target.field_70165_t, target.field_70163_u + target.field_70129_M, target.field_70161_v, SoundUtils.GHAST_FIREBALL.getSoundName(), 0.9F, 1.0F);
      return true;
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\server\manager\impl\DodgeManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */