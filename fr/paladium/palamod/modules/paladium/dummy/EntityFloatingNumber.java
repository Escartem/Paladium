package fr.paladium.palamod.modules.paladium.dummy;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityFloatingNumber extends Entity implements IEntityAdditionalSpawnData {
  protected int age;
  
  public float damage;
  
  protected int speed;
  
  public EntityFloatingNumber(World world) {
    super(world);
  }
  
  public EntityFloatingNumber(World world, float damage, double x, double y, double z) {
    super(world);
    this.damage = damage;
    this.field_70142_S = this.field_70165_t = x;
    this.field_70137_T = this.field_70163_u = y;
    this.field_70136_U = this.field_70161_v = z;
  }
  
  protected void func_70088_a() {
    this.age = 0;
    this.speed = 500;
  }
  
  public void func_70030_z() {
    if (this.age++ > 50)
      func_70106_y(); 
    this.field_70163_u += this.speed / 500.0D;
    if (this.speed > 1) {
      this.speed /= 2;
    } else if (this.speed == 1) {
      this.speed = 0;
    } 
  }
  
  public void func_70091_d(double p_70091_1_, double p_70091_3_, double p_70091_5_) {}
  
  public void reSet(float damage) {
    this.damage = damage;
    this.age = 0;
  }
  
  protected void func_70037_a(NBTTagCompound p_70037_1_) {}
  
  protected void func_70014_b(NBTTagCompound p_70014_1_) {}
  
  public void writeSpawnData(ByteBuf buffer) {
    buffer.writeFloat(this.damage);
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    this.damage = additionalData.readFloat();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\dummy\EntityFloatingNumber.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */