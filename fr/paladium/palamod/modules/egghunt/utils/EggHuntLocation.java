package fr.paladium.palamod.modules.egghunt.utils;

import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.util.BlockLocation;
import net.minecraft.entity.Entity;

public class EggHuntLocation {
  private int x;
  
  private int y;
  
  private int z;
  
  public EggHuntLocation(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public int getZ() {
    return this.z;
  }
  
  public EggHuntLocation(BlockLocation location) {
    this.x = location.getX();
    this.y = location.getY();
    this.z = location.getZ();
  }
  
  public EggHuntLocation(Entity entity) {
    BlockPos pos = new BlockPos(entity);
    this.x = pos.getX();
    this.y = pos.getY();
    this.z = pos.getZ();
  }
  
  public String format() {
    return "x: " + this.x + ", y: " + this.y + ", z: " + this.z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghun\\utils\EggHuntLocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */