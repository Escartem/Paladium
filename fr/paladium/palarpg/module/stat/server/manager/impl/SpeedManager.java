package fr.paladium.palarpg.module.stat.server.manager.impl;

import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;

public class SpeedManager {
  private static final UUID SPEED_MODIFIER_ID = UUIDUtils.from("1db8ef77-a0bd-48e0-95e3-891b2e1eeed8");
  
  public void applySpeed(EntityLivingBase entity) {
    RPGStatPlayerData statEntity = (RPGStatPlayerData)RPGPlayer.getData((Entity)entity, "stats");
    if (statEntity == null)
      return; 
    IAttributeInstance speedAttribute = entity.func_110148_a(SharedMonsterAttributes.field_111263_d);
    if (speedAttribute == null)
      return; 
    if (speedAttribute.func_111127_a(SPEED_MODIFIER_ID) != null)
      speedAttribute.func_111124_b(speedAttribute.func_111127_a(SPEED_MODIFIER_ID)); 
    entity.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111121_a(new AttributeModifier(SPEED_MODIFIER_ID, "palarpg_speed", ((Number)statEntity.getSpeed().getValue()).doubleValue() / 100.0D, 2));
  }
  
  public void resetSpeed(EntityLivingBase entity) {
    IAttributeInstance speedAttribute = entity.func_110148_a(SharedMonsterAttributes.field_111263_d);
    if (speedAttribute != null && speedAttribute.func_111127_a(SPEED_MODIFIER_ID) != null)
      speedAttribute.func_111124_b(speedAttribute.func_111127_a(SPEED_MODIFIER_ID)); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\server\manager\impl\SpeedManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */