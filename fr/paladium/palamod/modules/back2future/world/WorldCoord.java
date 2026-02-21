package fr.paladium.palamod.modules.back2future.world;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class WorldCoord implements Comparable<WorldCoord> {
  public int x;
  
  public int y;
  
  public int z;
  
  public WorldCoord(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public WorldCoord(TileEntity tile) {
    this.x = tile.field_145851_c;
    this.y = tile.field_145848_d;
    this.z = tile.field_145849_e;
  }
  
  public WorldCoord add(ForgeDirection dir) {
    return new WorldCoord(this.x + dir.offsetX, this.y + dir.offsetY, this.z + dir.offsetZ);
  }
  
  public int hashCode() {
    return (new HashCodeBuilder()).append(this.x).append(this.y).append(this.z).hashCode();
  }
  
  public int compareTo(WorldCoord wc) {
    int legthThis = this.x * this.x + this.y * this.y + this.z * this.z;
    int legthOther = wc.x * wc.x + wc.y * wc.y + wc.z * wc.z;
    return Integer.compare(legthThis, legthOther);
  }
  
  public boolean equals(Object obj) {
    if (!(obj instanceof WorldCoord))
      return false; 
    WorldCoord wc = (WorldCoord)obj;
    return (this.x == wc.x && this.y == wc.y && this.z == wc.z);
  }
  
  public String toString() {
    return "Coord: " + this.x + ", " + this.y + ", " + this.z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\world\WorldCoord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */