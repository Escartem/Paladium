package fr.paladium.palamod.modules.hunter.entites;

import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palajobs.core.pojo.objectives.types.EntityKillSpecialObjective;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.achievements.types.UseItemAchievement;
import fr.paladium.palamod.modules.hunter.utils.AEntityMobStaffSound;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.util.FastUUID;
import java.util.Random;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityElephant extends AEntityMobStaffSound {
  private int age;
  
  private int color;
  
  private int feed;
  
  private boolean tamed;
  
  private UUID owner;
  
  public int func_70654_ax() {
    return this.age;
  }
  
  public void setAge(int age) {
    this.age = age;
  }
  
  public int getColor() {
    return this.color;
  }
  
  public void setColor(int color) {
    this.color = color;
  }
  
  public int getFeed() {
    return this.feed;
  }
  
  public void setFeed(int feed) {
    this.feed = feed;
  }
  
  public boolean isTamed() {
    return this.tamed;
  }
  
  public void setTamed(boolean tamed) {
    this.tamed = tamed;
  }
  
  public UUID getOwner() {
    return this.owner;
  }
  
  public void setOwner(UUID owner) {
    this.owner = owner;
  }
  
  public EntityElephant(World world) {
    super(world);
    this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, EntityPlayer.class, 0.8D, false));
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.6D));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
  }
  
  public String getSoundName() {
    return "elephant";
  }
  
  public boolean func_70650_aV() {
    return true;
  }
  
  protected void func_70088_a() {
    this.age = this.field_70170_p.field_73012_v.nextInt(3);
    this.field_70180_af.func_75682_a(20, Integer.valueOf(this.age));
    this.color = 0;
    this.field_70180_af.func_75682_a(22, Integer.valueOf(this.color));
    this.feed = 0;
    this.field_70180_af.func_75682_a(21, Integer.valueOf(this.feed));
    this.tamed = false;
    this.field_70180_af.func_75682_a(23, Integer.valueOf(this.tamed ? 1 : 0));
    super.func_70088_a();
  }
  
  public void func_70645_a(DamageSource damageSource) {
    super.func_70645_a(damageSource);
    if (damageSource.func_76346_g() instanceof EntityPlayer) {
      EntityPlayer player = (EntityPlayer)damageSource.func_76346_g();
      JobsPlayer jobPlayer = JobsPlayer.get((Entity)player);
      if (jobPlayer != null) {
        int xp = 0;
        if (this.feed >= 128 && this.feed <= 1000)
          xp = (new Random()).nextInt(40) + 1; 
        if (this.feed > 1000)
          xp = this.feed / 100 + (new Random()).nextInt(this.feed / 10 + this.feed / 25); 
        if (xp > 0)
          EntityKillSpecialObjective.performCheck(player, getClass(), xp); 
      } 
    } 
  }
  
  public void func_70071_h_() {
    if (this.field_70170_p.field_72995_K) {
      this.age = this.field_70180_af.func_75679_c(20);
    } else {
      this.field_70180_af.func_75692_b(20, Integer.valueOf(this.age));
    } 
    if (this.field_70170_p.field_72995_K) {
      this.feed = this.field_70180_af.func_75679_c(21);
    } else {
      this.field_70180_af.func_75692_b(21, Integer.valueOf(this.feed));
    } 
    if (this.field_70170_p.field_72995_K) {
      this.color = this.field_70180_af.func_75679_c(22);
    } else {
      this.field_70180_af.func_75692_b(22, Integer.valueOf(this.color));
    } 
    if (this.field_70170_p.field_72995_K) {
      this.tamed = (this.field_70180_af.func_75679_c(23) == 1);
    } else {
      this.field_70180_af.func_75692_b(23, Integer.valueOf(this.tamed ? 1 : 0));
    } 
    if (this.age == 0) {
      func_70105_a(1.0F, 1.5F);
    } else if (this.age == 1) {
      func_70105_a(1.5F, 2.3F);
    } else if (this.age == 2) {
      func_70105_a(3.0F, 4.0F);
    } 
    super.func_70071_h_();
  }
  
  public void func_70109_d(NBTTagCompound compound) {
    compound.func_74768_a("color", this.color);
    compound.func_74768_a("age", this.age);
    compound.func_74768_a("feed", this.feed);
    compound.func_74757_a("tamed", this.tamed);
    if (this.owner != null)
      compound.func_74778_a("owner", FastUUID.toString(this.owner)); 
    super.func_70109_d(compound);
  }
  
  public void func_70020_e(NBTTagCompound compound) {
    if (compound.func_74764_b("color"))
      this.color = compound.func_74762_e("color"); 
    if (compound.func_74764_b("age"))
      this.age = compound.func_74762_e("age"); 
    if (compound.func_74764_b("feed"))
      this.feed = compound.func_74762_e("feed"); 
    if (compound.func_74764_b("tamed"))
      this.tamed = compound.func_74767_n("tamed"); 
    if (compound.func_74764_b("owner"))
      this.owner = FastUUID.parseUUID(compound.func_74779_i("owner")); 
    super.func_70020_e(compound);
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111263_d)
      .func_111128_a((this.age == 0) ? 0.6D : ((this.age == 1) ? 0.5D : 0.3D));
    func_110148_a(SharedMonsterAttributes.field_111267_a)
      .func_111128_a((this.age == 0) ? 25.0D : ((this.age == 1) ? 50.0D : 100.0D));
    func_110148_a(SharedMonsterAttributes.field_111264_e)
      .func_111128_a((this.age == 0) ? 5.0D : ((this.age == 1) ? 10.0D : 25.0D));
    func_110148_a(SharedMonsterAttributes.field_111266_c)
      .func_111128_a((this.age == 0.3D) ? 5.0D : ((this.age == 1) ? 0.65D : 1.0D));
  }
  
  public boolean func_70097_a(DamageSource source, float f) {
    if (source.func_76346_g() instanceof EntityLivingBase) {
      func_70784_b(source.func_76346_g());
      func_70624_b((EntityLivingBase)source.func_76346_g());
    } 
    return super.func_70097_a(source, f);
  }
  
  protected Item func_146068_u() {
    if (this.field_70170_p.field_73012_v.nextInt(100) < 4 && this.age > 0)
      return ItemsRegister.TUSK; 
    return Item.func_150899_d(0);
  }
  
  public void func_70030_z() {
    if (this.tamed && this.owner != null && 
      this.field_70153_n != null && 
      this.field_70153_n instanceof EntityPlayerMP) {
      EntityPlayerMP player = (EntityPlayerMP)this.field_70153_n;
      if (player.func_71045_bC() != null && 
        player.func_71045_bC().func_77973_b() == 
        Item.func_150898_a(BlocksRegister.BAMBOO)) {
        this.field_70177_z = this.field_70153_n.field_70177_z;
        func_70612_e(0.0F, (this.age == 0) ? 0.4F : ((this.age == 1) ? 0.3F : 0.2F));
        this.field_70138_W = 1.0F;
      } 
    } 
    super.func_70030_z();
  }
  
  protected boolean func_70085_c(EntityPlayer player) {
    if (this.tamed && this.owner != null && 
      player.func_71045_bC() == null && 
      player.func_110124_au().equals(this.owner))
      player.func_70078_a((Entity)this); 
    if (this.feed < 100000) {
      if (!this.tamed && this.age != 0)
        return super.func_70085_c(player); 
      func_110148_a(SharedMonsterAttributes.field_111263_d)
        .func_111128_a((this.age == 0) ? 0.6D : ((this.age == 1) ? 0.5D : 0.3D));
      func_110148_a(SharedMonsterAttributes.field_111267_a)
        .func_111128_a((this.age == 0) ? 25.0D : ((this.age == 1) ? 50.0D : 100.0D));
      func_110148_a(SharedMonsterAttributes.field_111264_e)
        .func_111128_a((this.age == 0) ? 5.0D : ((this.age == 1) ? 10.0D : 25.0D));
      func_110148_a(SharedMonsterAttributes.field_111266_c)
        .func_111128_a((this.age == 0.3D) ? 5.0D : ((this.age == 1) ? 0.65D : 1.0D));
      if (player.func_71045_bC() != null)
        if (player.func_71045_bC().func_77973_b() == 
          Item.func_150898_a(BlocksRegister.BAMBOO)) {
          boolean sneak = player.func_70093_af();
          int size = (player.func_71045_bC()).field_77994_a;
          if (sneak) {
            this.feed += size;
            func_70691_i(1.0F * size);
            UseItemAchievement.performCheck(player, player.func_71045_bC(), "BAMBOO", size);
          } else {
            this.feed++;
            func_70691_i(1.0F);
            UseItemAchievement.performCheck(player, player.func_71045_bC(), "BAMBOO", 1);
          } 
          EventUtils.spawnParticle(this.field_70170_p, (this.feed >= 100000) ? "heart" : "crit", this.field_70165_t, this.field_70163_u + 1.0D, this.field_70161_v, (this.feed >= 100000) ? 200 : 75, (this.feed >= 100000) ? 0.3D : 0.2D);
          if (!player.field_71075_bZ.field_75098_d)
            if (sneak) {
              (player.func_71045_bC()).field_77994_a -= size;
            } else {
              (player.func_71045_bC()).field_77994_a--;
            }  
          if (!this.tamed) {
            if (this.feed >= this.field_70170_p.field_73012_v.nextInt(10) && this.age == 0) {
              this.tamed = true;
              this.owner = player.func_110124_au();
              EventUtils.spawnParticle(this.field_70170_p, "heart", this.field_70165_t, this.field_70163_u + 1.0D, this.field_70161_v, 100, 0.15D);
            } 
          } else if (this.feed >= 64 && this.feed < 128) {
            this.age = 1;
          } else if (this.feed >= 128 && this.feed < 1000) {
            this.age = 2;
          } else if (this.feed >= 1000 && this.feed < 5000) {
            this.age = 2;
            this.color = 1;
          } else if (this.feed >= 5000) {
            this.age = 2;
            this.color = 2;
          } 
        } else if (player.func_71045_bC().func_77973_b() == 
          Item.func_150898_a(BlocksRegister.BAMBOO_BLOCK)) {
          boolean sneak = player.func_70093_af();
          int size = (player.func_71045_bC()).field_77994_a;
          if (sneak) {
            this.feed += size * 9;
            func_70691_i(9.0F * size);
            UseItemAchievement.performCheck(player, player.func_71045_bC(), "BAMBOO", size * 9);
          } else {
            this.feed += 9;
            func_70691_i(9.0F);
            UseItemAchievement.performCheck(player, player.func_71045_bC(), "BAMBOO", 9);
          } 
          EventUtils.spawnParticle(this.field_70170_p, "crit", this.field_70165_t, this.field_70163_u + 1.0D, this.field_70161_v, 75, 0.2D);
          if (!player.field_71075_bZ.field_75098_d)
            if (sneak) {
              (player.func_71045_bC()).field_77994_a -= size;
            } else {
              (player.func_71045_bC()).field_77994_a--;
            }  
          if (!this.tamed) {
            if (this.feed >= this.field_70170_p.field_73012_v.nextInt(10) && this.age == 0) {
              this.tamed = true;
              this.owner = player.func_110124_au();
              EventUtils.spawnParticle(this.field_70170_p, "heart", this.field_70165_t, this.field_70163_u + 1.0D, this.field_70161_v, 100, 0.15D);
            } 
          } else if (this.feed >= 64 && this.feed < 128) {
            this.age = 1;
          } else if (this.feed >= 128 && this.feed < 1000) {
            this.age = 2;
          } else if (this.feed >= 1000 && this.feed < 5000) {
            this.age = 2;
            this.color = 1;
          } else if (this.feed >= 5000) {
            this.age = 2;
            this.color = 2;
          } 
        }  
    } 
    return super.func_70085_c(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\entites\EntityElephant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */