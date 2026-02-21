package fr.paladium.palamod.modules.luckyblock.monthly.utils;

import net.minecraft.world.World;

class Bounds {
  int minX;
  
  int minY;
  
  int minZ;
  
  int maxX;
  
  int maxY;
  
  int maxZ;
  
  public int getMinX() {
    return this.minX;
  }
  
  public int getMinY() {
    return this.minY;
  }
  
  public int getMinZ() {
    return this.minZ;
  }
  
  public int getMaxX() {
    return this.maxX;
  }
  
  public int getMaxY() {
    return this.maxY;
  }
  
  public int getMaxZ() {
    return this.maxZ;
  }
  
  public Bounds(int x, int y, int z) {
    this.minX = this.maxX = x;
    this.minY = this.maxY = y;
    this.minZ = this.maxZ = z;
  }
  
  public void updateBounds(int x, int y, int z) {
    this.minX = Math.min(this.minX, x);
    this.minY = Math.min(this.minY, y);
    this.minZ = Math.min(this.minZ, z);
    this.maxX = Math.max(this.maxX, x);
    this.maxY = Math.max(this.maxY, y);
    this.maxZ = Math.max(this.maxZ, z);
  }
  
  public DoubleCuboid toCuboid(World world) {
    return new DoubleCuboid(world, this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthl\\utils\DoubleCuboid$Bounds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */