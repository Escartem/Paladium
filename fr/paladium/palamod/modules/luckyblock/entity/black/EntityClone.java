package fr.paladium.palamod.modules.luckyblock.entity.black;

import fr.paladium.palamod.modules.luckyblock.entity.ai.EntityAIFollowOwner;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityClone extends EntityTameable {
  public EntityClone(World world) {
    super(world);
    func_70105_a(0.6F, 1.65F);
    func_98053_h(true);
    func_70661_as().func_75491_a(true);
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, 1.0D, true));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
    this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIOwnerHurtByTarget(this));
    this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAIOwnerHurtTarget(this));
    this.field_70715_bh.func_75776_a(3, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true));
    this.field_70715_bh.func_75776_a(4, (EntityAIBase)new EntityAITargetNonTamed(this, EntityPlayer.class, 200, false));
  }
  
  public EntityClone(EntityPlayerMP player, World worldObj, int x, int y, int z) {
    this(worldObj);
    func_70107_b(x, y, z);
  }
  
  public boolean func_70686_a(Class p_70686_1_) {
    return true;
  }
  
  public boolean func_70652_k(Entity p_70652_1_) {
    float f = (float)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
    int i = 0;
    if (p_70652_1_ instanceof EntityLivingBase) {
      f += EnchantmentHelper.func_77512_a((EntityLivingBase)this, (EntityLivingBase)p_70652_1_);
      i += EnchantmentHelper.func_77507_b((EntityLivingBase)this, (EntityLivingBase)p_70652_1_);
    } 
    boolean flag = p_70652_1_.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), f);
    if (flag) {
      if (i > 0) {
        p_70652_1_.func_70024_g((
            -MathHelper.func_76126_a(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F), 0.1D, (
            
            MathHelper.func_76134_b(this.field_70177_z * 3.1415927F / 180.0F) * i * 0.5F));
        this.field_70159_w *= 0.6D;
        this.field_70179_y *= 0.6D;
      } 
      int j = EnchantmentHelper.func_90036_a((EntityLivingBase)this);
      if (j > 0)
        p_70652_1_.func_70015_d(j * 4); 
      if (p_70652_1_ instanceof EntityLivingBase)
        EnchantmentHelper.func_151384_a((EntityLivingBase)p_70652_1_, (Entity)this); 
      EnchantmentHelper.func_151385_b((EntityLivingBase)this, p_70652_1_);
    } 
    return flag;
  }
  
  public void func_70030_z() {
    super.func_70030_z();
    if (this.field_70173_aa % 20 == 0)
      func_70902_q(); 
  }
  
  protected void func_70619_bc() {
    super.func_70619_bc();
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    if (func_110148_a(SharedMonsterAttributes.field_111267_a) == null)
      func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111267_a); 
    if (func_110148_a(SharedMonsterAttributes.field_111264_e) == null)
      func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e); 
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(6.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
  }
  
  protected boolean func_70650_aV() {
    return true;
  }
  
  public void func_70037_a(NBTTagCompound p_70037_1_) {
    super.func_70037_a(p_70037_1_);
    func_70904_g(false);
  }
  
  public void func_70014_b(NBTTagCompound p_70014_1_) {
    super.func_70014_b(p_70014_1_);
  }
  
  public EntityAgeable func_90011_a(EntityAgeable p_90011_1_) {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\black\EntityClone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */