package fr.paladium.palamod.modules.luckyblock.entity.may;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDarkKnight extends EntityMob {
  private DamageSource damageSource;
  
  private int tickCpt = 0;
  
  private int immobilizeTimer = 0;
  
  private EntityPlayerMP target;
  
  private double targetY = 0.0D;
  
  public EntityDarkKnight(World world) {
    super(world);
    func_70661_as().func_75498_b(true);
    this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, EntityPlayer.class, 1.0D, false));
    this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIMoveThroughVillage((EntityCreature)this, 1.0D, false));
    this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true));
    this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
    func_70062_b(0, new ItemStack(ItemsRegister.LIGHT_SABER));
    func_70062_b(4, new ItemStack(ItemsRegister.DARK_KNIGHT_HELMET));
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(100.0D);
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(200.0D);
    func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(0.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(1.0D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(12.0D);
  }
  
  protected Entity func_70782_k() {
    EntityPlayer entityplayer = this.field_70170_p.func_72856_b((Entity)this, 64.0D);
    return (entityplayer != null) ? (Entity)entityplayer : null;
  }
  
  public boolean func_70652_k(Entity entity) {
    if (entity instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)entity;
      if (this.tickCpt >= 20 && this.tickCpt <= 220)
        dealDamage(player); 
      return true;
    } 
    return false;
  }
  
  public void func_70636_d() {
    super.func_70636_d();
    if (!this.field_70170_p.field_72995_K) {
      this.tickCpt++;
      if (this.immobilizeTimer >= 40) {
        if (this.target != null)
          dealDamage((EntityPlayer)this.target); 
        this.immobilizeTimer = 0;
        this.tickCpt = 0;
        this.target = null;
      } 
      if (this.tickCpt > 220) {
        if (this.target == null) {
          this.target = (EntityPlayerMP)this.field_70170_p.func_72890_a((Entity)this, 16.0D);
          if (this.target != null)
            this.targetY = this.target.field_70163_u; 
          return;
        } 
        this.field_70159_w = 0.0D;
        this.field_70179_y = 0.0D;
        this.target.field_70159_w = 0.0D;
        this.target.field_70179_y = 0.0D;
        this.target.field_70181_x = 0.0D;
        if (this.target.field_70163_u < this.targetY + 2.0D)
          this.target.field_70181_x = 0.1D; 
        this.target.field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity((Entity)this.target));
        this.immobilizeTimer++;
      } 
    } 
  }
  
  private DamageSource getDamageSource() {
    if (this.damageSource == null) {
      this.damageSource = DamageSource.func_76358_a((EntityLivingBase)this);
      this.damageSource.func_76348_h();
      this.damageSource.func_151518_m();
    } 
    return this.damageSource;
  }
  
  private void dealDamage(EntityPlayer player) {
    if (player.func_110143_aJ() - 4.0F > 2.0F) {
      player.func_70097_a(getDamageSource(), 6.0F);
    } else {
      func_70106_y();
    } 
  }
  
  protected void func_82164_bB() {}
  
  public void func_70645_a(DamageSource damageSource) {
    if (!this.field_70170_p.field_72995_K) {
      int random = (new Random()).nextInt(2);
      if (random == 1) {
        PlayerUtils.dropItemStack((Entity)this, this.field_70165_t, this.field_70163_u + 1.0D, this.field_70161_v, new ItemStack(ItemsRegister.DARK_KNIGHT_HELMET));
      } else {
        PlayerUtils.dropItemStack((Entity)this, this.field_70165_t, this.field_70163_u + 1.0D, this.field_70161_v, new ItemStack(ItemsRegister.LIGHT_SABER));
      } 
    } 
    super.func_70645_a(damageSource);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\may\EntityDarkKnight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */