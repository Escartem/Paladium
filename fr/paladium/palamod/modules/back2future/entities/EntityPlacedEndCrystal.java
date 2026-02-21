package fr.paladium.palamod.modules.back2future.entities;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityPlacedEndCrystal extends EntityEnderCrystal {
  private int blockX;
  
  private int blockY;
  
  private int blockZ;
  
  private int timer = 20 + this.field_70146_Z.nextInt(20);
  
  public EntityPlacedEndCrystal(World world) {
    super(world);
  }
  
  public void setBlockPos(int x, int y, int z) {
    this.blockX = x;
    this.blockY = y;
    this.blockZ = z;
  }
  
  public void func_70071_h_() {
    this.field_70169_q = this.field_70165_t;
    this.field_70167_r = this.field_70163_u;
    this.field_70166_s = this.field_70161_v;
    this.field_70261_a++;
    this.field_70180_af.func_75692_b(8, Integer.valueOf(this.field_70260_b));
    if (this.field_70170_p.field_72995_K || this.field_70128_L)
      return; 
    if (this.field_70170_p.func_72820_D() % this.timer == 0L && isOnSuitablePlace()) {
      this.timer = 20 + this.field_70146_Z.nextInt(20);
      List<EntityPlacedEndCrystal> crystals = this.field_70170_p.func_72872_a(getClass(), AxisAlignedBB.func_72330_a((this.blockX - 6), this.blockY, (this.blockZ - 6), (this.blockX + 6), (this.blockY + 1), (this.blockZ + 6)));
      if (crystals.size() == 4) {
        for (EntityPlacedEndCrystal crystal : crystals) {
          if (crystal.field_70128_L || !crystal.isOnSuitablePlace())
            return; 
        } 
        for (EntityPlacedEndCrystal crystal : crystals)
          crystal.func_70106_y(); 
        func_70106_y();
        EntityRespawnedDragon dragon = new EntityRespawnedDragon(this.field_70170_p);
        dragon.func_70107_b(this.field_70165_t, this.field_70163_u + 30.0D, this.field_70161_v);
        this.field_70170_p.func_72838_d((Entity)dragon);
      } 
    } 
  }
  
  private boolean isOnSuitablePlace() {
    if (this.field_70170_p.field_73011_w.field_76574_g == 1 && this.field_70170_p
      .func_147439_a(this.blockX, this.blockY, this.blockZ) == Blocks.field_150357_h) {
      int portalCount = 0;
      for (int i = -6; i <= 6; i++) {
        for (int j = -6; j <= 6; j++) {
          if (this.field_70170_p.func_147439_a(this.blockX + i, this.blockY, this.blockZ + j) == Blocks.field_150384_bq)
            portalCount++; 
        } 
      } 
      return (portalCount == 20);
    } 
    return false;
  }
  
  protected void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    nbt.func_74768_a("BlockX", this.blockX);
    nbt.func_74768_a("BlockY", this.blockY);
    nbt.func_74768_a("BlockZ", this.blockZ);
  }
  
  protected void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    this.blockX = nbt.func_74762_e("BlockX");
    this.blockY = nbt.func_74762_e("BlockY");
    this.blockZ = nbt.func_74762_e("BlockZ");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\EntityPlacedEndCrystal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */