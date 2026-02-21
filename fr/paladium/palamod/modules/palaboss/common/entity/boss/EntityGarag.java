package fr.paladium.palamod.modules.palaboss.common.entity.boss;

import fr.paladium.palamod.modules.palaboss.common.entity.ia.EntityAIGroundAttack;
import java.util.Random;
import net.minecraft.entity.Entity;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityGarag extends EntityBossBase implements IRangedAttackMob {
  public EntityGarag(World world) {
    super(world);
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIGroundAttack(this, 1.0D, 20, 10, 5.0F));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
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
  
  public void func_82196_d(EntityLivingBase p_82196_1_, float p_82196_2_) {}
  
  public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
    if (p_70097_1_.func_76346_g() != null)
      p_70097_1_.func_76364_f().func_70097_a(DamageSource.func_92087_a((Entity)this), 
          getAttributes().getAttributeFloat("thornDamages", 10.0F)); 
    return super.func_70097_a(p_70097_1_, p_70097_2_);
  }
  
  public int calculThornsDamage(int p_92095_0_, Random p_92095_1_) {
    return (p_92095_0_ > 10) ? (p_92095_0_ = 10) : (1 + p_92095_1_.nextInt(p_92095_0_));
  }
  
  public float getEntityWidth() {
    return 5.2F;
  }
  
  public float getEntityHeight() {
    return 4.2F;
  }
  
  public String name() {
    return "Garag";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\entity\boss\EntityGarag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */