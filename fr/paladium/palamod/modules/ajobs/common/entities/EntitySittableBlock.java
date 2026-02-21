package fr.paladium.palamod.modules.ajobs.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntitySittableBlock extends Entity {
  public int blockPosX;
  
  public int blockPosY;
  
  public int blockPosZ;
  
  public EntitySittableBlock(World world) {
    super(world);
    this.field_70145_X = true;
    this.field_70131_O = 0.01F;
    this.field_70130_N = 0.01F;
  }
  
  public EntitySittableBlock(World world, double x, double y, double z, double y0ffset) {
    this(world);
    this.blockPosX = (int)x;
    this.blockPosY = (int)y;
    this.blockPosZ = (int)z;
    func_70107_b(x + 0.5D, y + y0ffset, z + 0.5D);
  }
  
  public EntitySittableBlock(World world, double x, double y, double z, double y0ffset, int rotation, double rotationOffset) {
    this(world);
    this.blockPosX = (int)x;
    this.blockPosY = (int)y;
    this.blockPosZ = (int)z;
    setPostionConsideringRotation(x + 0.5D, y + y0ffset, z + 0.5D, rotation, rotationOffset);
  }
  
  public void setPostionConsideringRotation(double x, double y, double z, int rotation, double rotationOffset) {
    switch (rotation) {
      case 2:
        z += rotationOffset;
        break;
      case 0:
        z -= rotationOffset;
        break;
      case 3:
        x -= rotationOffset;
        break;
      case 1:
        x += rotationOffset;
        break;
    } 
    func_70107_b(x, y, z);
  }
  
  public double func_70042_X() {
    return (this.field_70131_O - 1.5F);
  }
  
  protected boolean func_142008_O() {
    return false;
  }
  
  public void func_70030_z() {
    if (!this.field_70170_p.field_72995_K && (
      this.field_70153_n == null || this.field_70170_p.func_147437_c(this.blockPosX, this.blockPosY, this.blockPosZ)))
      func_70106_y(); 
  }
  
  protected void func_70088_a() {}
  
  public void func_70037_a(NBTTagCompound nbttagcompound) {}
  
  public void func_70014_b(NBTTagCompound nbttagcompound) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\ajobs\common\entities\EntitySittableBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */