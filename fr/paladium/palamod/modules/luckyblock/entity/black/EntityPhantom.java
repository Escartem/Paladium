package fr.paladium.palamod.modules.luckyblock.entity.black;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityPhantom extends EntityMob {
  private boolean field_146076_bu = false;
  
  private final EntityAIBreakDoor field_146075_bs = new EntityAIBreakDoor((EntityLiving)this);
  
  public EntityPhantom(World p_i1745_1_) {
    super(p_i1745_1_);
    func_70661_as().func_75498_b(true);
    this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, EntityPlayer.class, 1.0D, false));
    this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true));
    this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
    func_70105_a(0.6F, 1.8F);
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.23000000417232513D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0D);
  }
  
  protected boolean func_70650_aV() {
    return true;
  }
  
  public boolean func_146072_bX() {
    return this.field_146076_bu;
  }
  
  public boolean func_70652_k(Entity p_70652_1_) {
    boolean flag = super.func_70652_k(p_70652_1_);
    if (flag) {
      int i = this.field_70170_p.field_73013_u.func_151525_a();
      if (func_70694_bm() == null && func_70027_ad() && this.field_70146_Z.nextFloat() < i * 0.3F)
        p_70652_1_.func_70015_d(2 * i); 
    } 
    return flag;
  }
  
  protected Item func_146068_u() {
    return Items.field_151106_aX;
  }
  
  public EnumCreatureAttribute func_70668_bt() {
    return EnumCreatureAttribute.UNDEAD;
  }
  
  public void func_70014_b(NBTTagCompound p_70014_1_) {
    super.func_70014_b(p_70014_1_);
    p_70014_1_.func_74757_a("CanBreakDoors", func_146072_bX());
  }
  
  public void func_70037_a(NBTTagCompound p_70037_1_) {
    super.func_70037_a(p_70037_1_);
    func_146070_a(p_70037_1_.func_74767_n("CanBreakDoors"));
  }
  
  public void func_146070_a(boolean p_146070_1_) {
    if (this.field_146076_bu != p_146070_1_) {
      this.field_146076_bu = p_146070_1_;
      if (p_146070_1_) {
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)this.field_146075_bs);
      } else {
        this.field_70714_bg.func_85156_a((EntityAIBase)this.field_146075_bs);
      } 
    } 
  }
  
  protected boolean func_70692_ba() {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\black\EntityPhantom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */