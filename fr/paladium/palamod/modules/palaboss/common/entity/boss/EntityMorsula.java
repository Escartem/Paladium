package fr.paladium.palamod.modules.palaboss.common.entity.boss;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.palaboss.common.entity.ia.EntityAIGroundAttack;
import java.util.List;
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
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityMorsula extends EntityBossBase implements IRangedAttackMob {
  private boolean shockwaveTeeth = false;
  
  private boolean charge;
  
  public EntityMorsula(World world) {
    super(world);
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIGroundAttack(this, 1.0D, 20, 20, 5.0F));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
    this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
  }
  
  public void func_70071_h_() {
    if (!this.field_70170_p.field_72995_K && 
      this.charge)
      for (Entity entity : getEntitiesIn(5)) {
        if (entity instanceof EntityLivingBase && !(entity instanceof EntityMorsula)) {
          entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), 
              getAttributes().getAttributeFloat("chargeDamages", 75.0F));
          entity.func_70024_g((
              -MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * 3.5F * 0.5F), 0.1D, (
              
              MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * 3.5F * 0.5F));
          entity.field_70181_x += 0.6000000059604645D;
          setCharge(false);
        } 
      }  
    super.func_70071_h_();
  }
  
  public List<Entity> getEntitiesIn(int radius) {
    return this.field_70170_p.func_72872_a(Entity.class, this.field_70121_D
        .func_72314_b(radius, radius, radius));
  }
  
  @SideOnly(Side.CLIENT)
  public void func_70103_a(byte p_70103_1_) {
    if (p_70103_1_ == 4) {
      this.shockwaveTeeth = true;
    } else if (p_70103_1_ == 5) {
      this.shockwaveTeeth = false;
    } else {
      super.func_70103_a(p_70103_1_);
    } 
  }
  
  public void setShockwaveTeeth(boolean shockwaveTeeth) {
    this.shockwaveTeeth = shockwaveTeeth;
  }
  
  protected boolean func_70650_aV() {
    return true;
  }
  
  protected boolean func_70692_ba() {
    return false;
  }
  
  public void func_82196_d(EntityLivingBase p_82196_1_, float p_82196_2_) {}
  
  public float getEntityWidth() {
    return 2.0F;
  }
  
  public float getEntityHeight() {
    return 3.7F;
  }
  
  public float func_70689_ay() {
    return this.charge ? (super.func_70689_ay() * 4.0F) : super.func_70689_ay();
  }
  
  public boolean isShockwaveTeeth() {
    return this.shockwaveTeeth;
  }
  
  public boolean isInCharge() {
    return this.charge;
  }
  
  public void setCharge(boolean charge) {
    this.charge = charge;
  }
  
  public String name() {
    return "Morsula";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\entity\boss\EntityMorsula.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */