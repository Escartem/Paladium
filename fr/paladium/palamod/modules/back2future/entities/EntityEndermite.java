package fr.paladium.palamod.modules.back2future.entities;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEndermite extends EntityMob {
  private final EntityAINearestAttackableTarget.Sorter sorter;
  
  private int lifetime = 0;
  
  private boolean playerSpawned = false;
  
  public EntityEndermite(World world) {
    super(world);
    this.sorter = new EntityAINearestAttackableTarget.Sorter((Entity)this);
    this.field_70728_aV = 3;
    func_70105_a(0.4F, 0.3F);
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, EntityPlayer.class, 1.0D, false));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true));
    this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 10, true));
  }
  
  public float func_70047_e() {
    return 0.1F;
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(8.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D);
  }
  
  protected boolean func_70041_e_() {
    return false;
  }
  
  protected String func_70639_aQ() {
    return "mob.silverfish.say";
  }
  
  protected String func_70621_aR() {
    return "mob.silverfish.hit";
  }
  
  protected String func_70673_aS() {
    return "mob.silverfish.kill";
  }
  
  protected void func_145780_a(int x, int y, int z, Block block) {
    func_85030_a("mob.silverfish.step", 0.15F, 1.0F);
  }
  
  protected Item func_146068_u() {
    return null;
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    this.lifetime = nbt.func_74762_e("Lifetime");
    this.playerSpawned = nbt.func_74767_n("PlayerSpawned");
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    nbt.func_74768_a("Lifetime", this.lifetime);
    nbt.func_74757_a("PlayerSpawned", this.playerSpawned);
  }
  
  public void func_70071_h_() {
    this.field_70761_aq = this.field_70177_z;
    super.func_70071_h_();
  }
  
  public boolean isSpawnedByPlayer() {
    return this.playerSpawned;
  }
  
  public void setSpawnedByPlayer(boolean spawnedByPlayer) {
    this.playerSpawned = spawnedByPlayer;
  }
  
  public void func_70636_d() {
    super.func_70636_d();
    if (this.field_70170_p.field_72995_K) {
      for (int i = 0; i < 2; i++)
        this.field_70170_p.func_72869_a("portal", this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5D) * this.field_70130_N, this.field_70163_u + this.field_70146_Z
            .nextDouble() * this.field_70131_O, this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5D) * this.field_70130_N, (this.field_70146_Z
            .nextDouble() - 0.5D) * 2.0D, -this.field_70146_Z.nextDouble(), (this.field_70146_Z
            .nextDouble() - 0.5D) * 2.0D); 
    } else {
      if (!func_104002_bU())
        this.lifetime++; 
      if (this.lifetime >= 2400)
        func_70106_y(); 
    } 
    if (isSpawnedByPlayer()) {
      double range = 64.0D;
      double radius = range / 2.0D;
      int tagetChance = 10;
      if (this.field_70146_Z.nextInt(tagetChance) != 0) {
        List<EntityEnderman> list = this.field_70170_p.func_94576_a((Entity)this, 
            AxisAlignedBB.func_72330_a(this.field_70165_t - radius, this.field_70163_u - 4.0D, this.field_70161_v - radius, this.field_70165_t + radius, this.field_70163_u + 4.0D, this.field_70161_v + radius), new IEntitySelector() {
              public boolean func_82704_a(Entity entity) {
                return entity instanceof EntityEnderman;
              }
            });
        Collections.sort(list, (Comparator<? super EntityEnderman>)this.sorter);
        if (!list.isEmpty()) {
          EntityEnderman enderman = list.get(0);
          enderman.func_70784_b((Entity)this);
        } 
      } 
    } 
  }
  
  protected boolean func_70814_o() {
    return true;
  }
  
  public boolean func_70601_bi() {
    if (super.func_70601_bi()) {
      EntityPlayer entityplayer = this.field_70170_p.func_72890_a((Entity)this, 5.0D);
      return (entityplayer == null);
    } 
    return false;
  }
  
  public EnumCreatureAttribute func_70668_bt() {
    return EnumCreatureAttribute.ARTHROPOD;
  }
  
  public ItemStack getPickedResult(MovingObjectPosition target) {
    return ModEntityList.getEggFor((Class)getClass());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\EntityEndermite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */