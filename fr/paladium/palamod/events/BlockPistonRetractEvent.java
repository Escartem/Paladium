package fr.paladium.palamod.events;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palaforgeutils.lib.location.BlockLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockPistonRetractEvent extends Event {
  private final World world;
  
  private final int x;
  
  private final int y;
  
  private final int z;
  
  private final ForgeDirection direction;
  
  public BlockPistonRetractEvent(World world, int x, int y, int z, ForgeDirection direction) {
    this.world = world;
    this.x = x;
    this.y = y;
    this.z = z;
    this.direction = direction;
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
  
  public BlockLocation getBlock() {
    return new BlockLocation(this.world, this.x, this.y, this.z);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\events\BlockPistonRetractEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */