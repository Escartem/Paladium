package fr.paladium.palawither.common.wither.base.ai;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;

public class WitherEntitySelector implements IEntitySelector {
  public boolean func_82704_a(Entity entity) {
    return (entity instanceof EntityLivingBase && ((EntityLivingBase)entity).func_70668_bt() != EnumCreatureAttribute.UNDEAD);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\wither\base\ai\WitherEntitySelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */