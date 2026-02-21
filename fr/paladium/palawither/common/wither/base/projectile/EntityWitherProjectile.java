package fr.paladium.palawither.common.wither.base.projectile;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public abstract class EntityWitherProjectile extends EntityFireball implements IEntityAdditionalSpawnData {
  public EntityWitherProjectile(@NonNull World world) {
    super(world);
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
  }
  
  public EntityWitherProjectile(@NonNull World world, @NonNull EntityLivingBase shootingEntity, double motionX, double motionY, double motionZ) {
    super(world, shootingEntity, motionX, motionY, motionZ);
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    if (shootingEntity == null)
      throw new NullPointerException("shootingEntity is marked non-null but is null"); 
  }
  
  protected final float func_82341_c() {
    return getSpeed();
  }
  
  protected void func_70227_a(MovingObjectPosition mop) {
    if (this.field_70170_p.field_72995_K)
      return; 
    if (mop.field_72308_g != null) {
      if (this.field_70235_a != null) {
        float damage = (float)this.field_70235_a.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
        if (mop.field_72308_g.func_70097_a(DamageSource.func_76358_a(this.field_70235_a), damage) && !mop.field_72308_g.func_70089_S())
          this.field_70235_a.func_70691_i(damage); 
      } 
      if (mop.field_72308_g instanceof EntityLivingBase)
        hit((EntityLivingBase)mop.field_72308_g); 
    } 
    if (isExplosive())
      explode(); 
    func_70106_y();
  }
  
  public boolean func_70067_L() {
    return false;
  }
  
  public boolean func_70097_a(DamageSource source, float damage) {
    return false;
  }
  
  public boolean func_70027_ad() {
    return false;
  }
  
  public boolean isExplosive() {
    return true;
  }
  
  public float getSpeed() {
    return super.func_82341_c();
  }
  
  public void hit(@NonNull EntityLivingBase entity) {
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    byte b0 = 0;
    if (this.field_70170_p.field_73013_u == EnumDifficulty.NORMAL) {
      b0 = 10;
    } else if (this.field_70170_p.field_73013_u == EnumDifficulty.HARD) {
      b0 = 40;
    } 
    if (b0 > 0)
      entity.func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, 20 * b0, 1)); 
  }
  
  public void explode() {
    this.field_70170_p.func_72885_a((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0F, false, this.field_70170_p.func_82736_K().func_82766_b("mobGriefing"));
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    func_70107_b(additionalData.readDouble(), additionalData.readDouble(), additionalData.readDouble());
    this.field_70159_w = additionalData.readDouble();
    this.field_70181_x = additionalData.readDouble();
    this.field_70179_y = additionalData.readDouble();
    this.field_70177_z = additionalData.readFloat();
    this.field_70125_A = additionalData.readFloat();
    this.field_70232_b = additionalData.readDouble();
    this.field_70233_c = additionalData.readDouble();
    this.field_70230_d = additionalData.readDouble();
    this.field_70235_a = (EntityLivingBase)this.field_70170_p.func_73045_a(additionalData.readInt());
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    buffer.writeDouble(this.field_70165_t);
    buffer.writeDouble(this.field_70163_u);
    buffer.writeDouble(this.field_70161_v);
    buffer.writeDouble(this.field_70159_w);
    buffer.writeDouble(this.field_70181_x);
    buffer.writeDouble(this.field_70179_y);
    buffer.writeFloat(this.field_70177_z);
    buffer.writeFloat(this.field_70125_A);
    buffer.writeDouble(this.field_70232_b);
    buffer.writeDouble(this.field_70233_c);
    buffer.writeDouble(this.field_70230_d);
    buffer.writeInt((this.field_70235_a != null) ? this.field_70235_a.func_145782_y() : -1);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\wither\base\projectile\EntityWitherProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */