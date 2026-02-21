package fr.paladium.palamod.events;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palaforgeutils.lib.location.BlockLocation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPistonExtendEvent extends Event {
  private final World world;
  
  private final int x;
  
  private final int y;
  
  private final int z;
  
  private final ForgeDirection direction;
  
  private final int length;
  
  private List<BlockLocation> blocks;
  
  public BlockPistonExtendEvent(World world, int x, int y, int z, ForgeDirection direction, int length) {
    this.world = world;
    this.x = x;
    this.y = y;
    this.z = z;
    this.direction = direction;
    this.length = length;
  }
  
  public World getWorld() {
    return this.world;
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
  
  public ForgeDirection getDirection() {
    return this.direction;
  }
  
  public int getLength() {
    return this.length;
  }
  
  public List<BlockLocation> getBlocks() {
    if (this.blocks == null) {
      List<BlockLocation> tmp = new ArrayList<>();
      for (int i = 0; i < getLength(); i++) {
        int ox = this.x + this.direction.offsetX * (i + 1);
        int oy = this.y + this.direction.offsetY * (i + 1);
        int oz = this.z + this.direction.offsetZ * (i + 1);
        tmp.add(new BlockLocation(this.world, ox, oy, oz));
      } 
      this.blocks = Collections.unmodifiableList(tmp);
    } 
    return this.blocks;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\events\BlockPistonExtendEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */