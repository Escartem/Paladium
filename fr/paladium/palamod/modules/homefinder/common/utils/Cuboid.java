package fr.paladium.palamod.modules.homefinder.common.utils;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class Cuboid {
  private final int x1;
  
  private final int y1;
  
  private final int z1;
  
  private final int x2;
  
  private final int y2;
  
  private final int z2;
  
  public Cuboid(Location minLocation, Location maxLocation) {
    this.x1 = (int)minLocation.getX();
    this.y1 = (int)minLocation.getY();
    this.z1 = (int)minLocation.getZ();
    this.x2 = (int)maxLocation.getX();
    this.y2 = (int)maxLocation.getY();
    this.z2 = (int)maxLocation.getZ();
  }
  
  public Cuboid(int x1, int y1, int z1, int x2, int y2, int z2) {
    this.x1 = x1;
    this.y1 = y1;
    this.z1 = z1;
    this.x2 = x2;
    this.y2 = y2;
    this.z2 = z2;
  }
  
  public List<Block> getBlocks(World world) {
    List<Block> blocks = new ArrayList<>();
    for (int x = this.x1; x <= this.x2; x++) {
      for (int y = this.y1; y <= this.y2; y++) {
        for (int z = this.z1; z <= this.z2; z++) {
          world.func_147468_f(x, y, z);
          blocks.add(world.func_147439_a(x, y, z));
        } 
      } 
    } 
    return blocks;
  }
  
  public void setBlocks(World world, Block block) {
    for (int x = this.x1; x <= this.x2; x++) {
      for (int y = this.y1; y <= this.y2; y++) {
        for (int z = this.z1; z <= this.z2; z++)
          world.func_147449_b(x, y, z, block); 
      } 
    } 
  }
  
  public List<Block> getBlocksByType(World world, Block block) {
    List<Block> blocks = new ArrayList<>();
    for (int x = this.x1; x <= this.x2; x++) {
      for (int y = this.y1; y <= this.y2; y++) {
        for (int z = this.z1; z <= this.z2; z++) {
          if (world.func_147439_a(x, y, z) == block)
            blocks.add(world.func_147439_a(x, y, z)); 
        } 
      } 
    } 
    return blocks;
  }
  
  public Location getCenter() {
    return new Location(((this.x1 + this.x2) / 2), ((this.y1 + this.y2) / 2), ((this.z1 + this.z2) / 2));
  }
  
  public boolean isInside(EntityPlayer player) {
    return isInside((int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
  }
  
  public boolean isInside(Location location) {
    return isInside((int)location.getX(), (int)location.getY(), (int)location.getZ());
  }
  
  public boolean isInside(int x, int y, int z) {
    return (x >= this.x1 && x <= this.x2 && y >= this.y1 && y <= this.y2 && z >= this.z1 && z <= this.z2);
  }
  
  public int getMinX() {
    return Math.min(this.x1, this.x2);
  }
  
  public int getMinY() {
    return Math.min(this.y1, this.y2);
  }
  
  public int getMinZ() {
    return Math.min(this.z1, this.z2);
  }
  
  public int getMaxX() {
    return Math.max(this.x1, this.x2);
  }
  
  public int getMaxY() {
    return Math.max(this.y1, this.y2);
  }
  
  public int getMaxZ() {
    return Math.max(this.z1, this.z2);
  }
  
  public int getVolume() {
    return getSizeX() * getSizeY() * getSizeZ();
  }
  
  public int getSizeX() {
    return Math.abs(this.x1 - this.x2) + 1;
  }
  
  public int getSizeY() {
    return Math.abs(this.y1 - this.y2) + 1;
  }
  
  public int getSizeZ() {
    return Math.abs(this.z1 - this.z2) + 1;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\homefinder\commo\\utils\Cuboid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */