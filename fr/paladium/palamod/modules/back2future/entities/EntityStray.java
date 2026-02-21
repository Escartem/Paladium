package fr.paladium.palamod.modules.back2future.entities;

import java.util.Calendar;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityStray extends EntitySkeleton {
  private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack((IRangedAttackMob)this, 1.0D, 20, 60, 15.0F);
  
  private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide((EntityCreature)this, EntityPlayer.class, 1.2D, false);
  
  public EntityStray(World p_i1741_1_) {
    super(p_i1741_1_);
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIRestrictSun((EntityCreature)this));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIFleeSun((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
    this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
    if (p_i1741_1_ != null && !p_i1741_1_.field_72995_K)
      func_85036_m(); 
  }
  
  public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_) {
    p_110161_1_ = super.func_110161_a(p_110161_1_);
    if (func_70681_au().nextInt(4) > 3) {
      this.field_70714_bg.func_75776_a(4, (EntityAIBase)this.aiAttackOnCollide);
      func_70062_b(0, new ItemStack(Items.field_151052_q));
      func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
      func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.27D);
    } else {
      this.field_70714_bg.func_75776_a(4, (EntityAIBase)this.aiArrowAttack);
      func_82164_bB();
      func_82162_bC();
    } 
    func_98053_h(
        (this.field_70146_Z.nextFloat() < 0.55F * this.field_70170_p.func_147462_b(this.field_70165_t, this.field_70163_u, this.field_70161_v)));
    if (func_71124_b(4) == null) {
      Calendar calendar = this.field_70170_p.func_83015_S();
      if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.field_70146_Z.nextFloat() < 0.25F) {
        func_70062_b(4, new ItemStack(
              (this.field_70146_Z.nextFloat() < 0.1F) ? Blocks.field_150428_aP : Blocks.field_150423_aK));
        this.field_82174_bp[4] = 0.0F;
      } 
    } 
    return p_110161_1_;
  }
  
  protected void func_82164_bB() {
    super.func_82164_bB();
    func_70062_b(0, new ItemStack((Item)Items.field_151031_f));
  }
  
  public boolean func_70652_k(Entity entity) {
    boolean flag = super.func_70652_k(entity);
    if (flag) {
      int i = this.field_70170_p.field_73013_u.func_151525_a();
      if (func_70694_bm() == null && func_70027_ad() && this.field_70146_Z.nextFloat() < i * 0.3F)
        entity.func_70015_d(0 * i); 
      ((EntityLivingBase)entity)
        .func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 140 * i, 0));
    } 
    return flag;
  }
  
  public void func_82196_d(EntityLivingBase p_82196_1_, float p_82196_2_) {
    EntityTippedArrow entityarrow = new EntityTippedArrow(this.field_70170_p, (EntityLivingBase)this, p_82196_1_, 1.6F, (14 - this.field_70170_p.field_73013_u.func_151525_a() * 4));
    int i = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, func_70694_bm());
    int j = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, func_70694_bm());
    entityarrow.func_70239_b((p_82196_2_ * 2.0F) + this.field_70146_Z.nextGaussian() * 0.25D + (this.field_70170_p.field_73013_u
        .func_151525_a() * 0.11F));
    int diff = this.field_70170_p.field_73013_u.func_151525_a();
    entityarrow.setEffect(new PotionEffect(Potion.field_76421_d.func_76396_c(), 600, 0));
    if (i > 0)
      entityarrow.func_70239_b(entityarrow.func_70242_d() + i * 0.5D + 0.5D); 
    if (j > 0)
      entityarrow.func_70240_a(j); 
    if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, func_70694_bm()) > 0 || 
      func_82202_m() == 1)
      entityarrow.func_70015_d(100); 
    func_85030_a("random.bow", 1.0F, 1.0F / (func_70681_au().nextFloat() * 0.4F + 0.8F));
    this.field_70170_p.func_72838_d((Entity)entityarrow);
  }
  
  protected void func_70628_a(boolean p_70628_1_, int p_70628_2_) {
    int j = this.field_70146_Z.nextInt(3 + p_70628_2_);
    int k;
    for (k = 0; k < j; k++)
      func_145779_a(Items.field_151032_g, 1); 
    for (k = 0; k < j; k++)
      func_145779_a(Items.field_151103_aS, 1); 
  }
  
  protected String func_70639_aQ() {
    return "palamod:mob.stray.idle";
  }
  
  protected String func_70621_aR() {
    return "palamod:mob.stray.hurt";
  }
  
  protected String func_70673_aS() {
    return "palamod:mob.stray.death";
  }
  
  protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
    func_85030_a("palamod:mob.stray.step", 0.15F, 1.0F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\EntityStray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */