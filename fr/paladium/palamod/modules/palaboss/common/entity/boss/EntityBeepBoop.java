package fr.paladium.palamod.modules.palaboss.common.entity.boss;

import fr.paladium.palamod.modules.palaboss.common.entity.ia.EntityAIGroundAttack;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityBeepBoop extends EntityBossBase implements IRangedAttackMob {
  public EntityBeepBoop(World world) {
    super(world);
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIGroundAttack(this, 1.0D, 10, 45, 7.0F));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
    this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
  }
  
  protected boolean func_70650_aV() {
    return true;
  }
  
  protected boolean func_70692_ba() {
    return false;
  }
  
  public boolean func_70097_a(DamageSource source, float f) {
    if (source.func_76352_a())
      f /= 2.0F; 
    return super.func_70097_a(source, f);
  }
  
  public void func_82196_d(EntityLivingBase p_82196_1_, float p_82196_2_) {}
  
  public float getEntityWidth() {
    return 2.0F;
  }
  
  public float getEntityHeight() {
    return 3.7F;
  }
  
  public String name() {
    return "BeepBoop";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\entity\boss\EntityBeepBoop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */