package fr.paladium.palamod.modules.luckyblock.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.ai.EntityAIFakePoweredCreeperSwell;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityFakePoweredCreeper extends EntityMob {
  private int lastActiveTime;
  
  private int timeSinceIgnited;
  
  private int fuseTime = 30;
  
  private int explosionRadius = 3;
  
  private static final String __OBFID = "CL_00001684";
  
  public EntityFakePoweredCreeper(World p_i1733_1_) {
    super(p_i1733_1_);
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIFakePoweredCreeperSwell(this));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAvoidEntity((EntityCreature)this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
    this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, 1.0D, false));
    this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.8D));
    this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
    this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
  }
  
  public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
    return false;
  }
  
  public boolean func_70650_aV() {
    return true;
  }
  
  public int func_82143_as() {
    return (func_70638_az() == null) ? 3 : (3 + (int)(func_110143_aJ() - 1.0F));
  }
  
  protected void func_70069_a(float p_70069_1_) {}
  
  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(16, Byte.valueOf((byte)-1));
    this.field_70180_af.func_75682_a(17, Byte.valueOf((byte)1));
    this.field_70180_af.func_75682_a(18, Byte.valueOf((byte)0));
  }
  
  public void func_70014_b(NBTTagCompound p_70014_1_) {
    super.func_70014_b(p_70014_1_);
    if (this.field_70180_af.func_75683_a(17) == 1)
      p_70014_1_.func_74757_a("powered", true); 
    p_70014_1_.func_74777_a("Fuse", (short)this.fuseTime);
    p_70014_1_.func_74774_a("ExplosionRadius", (byte)this.explosionRadius);
    p_70014_1_.func_74757_a("ignited", func_146078_ca());
  }
  
  public void func_70037_a(NBTTagCompound p_70037_1_) {
    super.func_70037_a(p_70037_1_);
    this.field_70180_af.func_75692_b(17, 
        Byte.valueOf((byte)(p_70037_1_.func_74767_n("powered") ? 1 : 0)));
    if (p_70037_1_.func_150297_b("Fuse", 99))
      this.fuseTime = p_70037_1_.func_74765_d("Fuse"); 
    if (p_70037_1_.func_150297_b("ExplosionRadius", 99))
      this.explosionRadius = p_70037_1_.func_74771_c("ExplosionRadius"); 
    if (p_70037_1_.func_74767_n("ignited"))
      func_146079_cb(); 
  }
  
  public void func_70071_h_() {
    if (func_70089_S()) {
      this.lastActiveTime = this.timeSinceIgnited;
      if (func_146078_ca())
        setCreeperState(1); 
      int i = getCreeperState();
      if (i > 0 && this.timeSinceIgnited == 0)
        func_85030_a("creeper.primed", 1.0F, 0.5F); 
      this.timeSinceIgnited += i;
      if (this.timeSinceIgnited < 0)
        this.timeSinceIgnited = 0; 
      if (this.timeSinceIgnited >= this.fuseTime) {
        this.timeSinceIgnited = this.fuseTime;
        func_146077_cc();
      } 
    } 
    super.func_70071_h_();
  }
  
  protected String func_70621_aR() {
    return "mob.creeper.say";
  }
  
  protected String func_70673_aS() {
    return "mob.creeper.death";
  }
  
  public void func_70645_a(DamageSource p_70645_1_) {
    super.func_70645_a(p_70645_1_);
    if (p_70645_1_.func_76346_g() instanceof net.minecraft.entity.monster.EntitySkeleton) {
      int i = Item.func_150891_b(Items.field_151096_cd);
      int j = Item.func_150891_b(Items.field_151084_co);
      int k = i + this.field_70146_Z.nextInt(j - i + 1);
      func_145779_a(Item.func_150899_d(k), 1);
    } 
  }
  
  public boolean func_70652_k(Entity p_70652_1_) {
    return true;
  }
  
  public boolean getPowered() {
    return (this.field_70180_af.func_75683_a(17) == 1);
  }
  
  @SideOnly(Side.CLIENT)
  public float getCreeperFlashIntensity(float p_70831_1_) {
    return (this.lastActiveTime + (this.timeSinceIgnited - this.lastActiveTime) * p_70831_1_) / (this.fuseTime - 2);
  }
  
  protected Item func_146068_u() {
    return Items.field_151016_H;
  }
  
  public int getCreeperState() {
    return this.field_70180_af.func_75683_a(16);
  }
  
  public void setCreeperState(int p_70829_1_) {
    this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)p_70829_1_));
  }
  
  public void func_70077_a(EntityLightningBolt p_70077_1_) {
    super.func_70077_a(p_70077_1_);
    this.field_70180_af.func_75692_b(17, Byte.valueOf((byte)1));
  }
  
  protected boolean func_70085_c(EntityPlayer p_70085_1_) {
    ItemStack itemstack = p_70085_1_.field_71071_by.func_70448_g();
    if (itemstack != null && itemstack.func_77973_b() == Items.field_151033_d) {
      this.field_70170_p.func_72908_a(this.field_70165_t + 0.5D, this.field_70163_u + 0.5D, this.field_70161_v + 0.5D, "fire.ignite", 1.0F, this.field_70146_Z
          .nextFloat() * 0.4F + 0.8F);
      p_70085_1_.func_71038_i();
      if (!this.field_70170_p.field_72995_K) {
        func_146079_cb();
        itemstack.func_77972_a(1, (EntityLivingBase)p_70085_1_);
        return true;
      } 
    } 
    return super.func_70085_c(p_70085_1_);
  }
  
  private void func_146077_cc() {
    for (int i = 0; i < 200; i++) {
      double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
      double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
      double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
      double d3 = 10.0D;
      this.field_70170_p.func_72869_a("dripWater", this.field_70165_t + (this.field_70146_Z
          .nextFloat() * this.field_70130_N * 2.0F) - this.field_70130_N - d0 * d3, this.field_70163_u + (this.field_70146_Z
          
          .nextFloat() * this.field_70131_O) - d1 * d3, this.field_70161_v + (this.field_70146_Z
          .nextFloat() * this.field_70130_N * 2.0F) - this.field_70130_N - d2 * d3, d0, d1, d2);
    } 
    func_70106_y();
  }
  
  public boolean func_146078_ca() {
    return (this.field_70180_af.func_75683_a(18) != 0);
  }
  
  public void func_146079_cb() {
    this.field_70180_af.func_75692_b(18, Byte.valueOf((byte)1));
  }
  
  protected void func_70665_d(DamageSource p_70665_1_, float p_70665_2_) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\EntityFakePoweredCreeper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */