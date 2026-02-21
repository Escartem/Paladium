package fr.paladium.palamixins.event;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockAnvilActivatedEvent extends Event {
  private final World world;
  
  private final int x;
  
  private final int y;
  
  private final int z;
  
  private final EntityPlayer player;
  
  private final int side;
  
  private final float hitX;
  
  private final float hitY;
  
  private final float hitZ;
  
  public BlockAnvilActivatedEvent(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    this.world = world;
    this.x = x;
    this.y = y;
    this.z = z;
    this.player = player;
    this.side = side;
    this.hitX = hitX;
    this.hitY = hitY;
    this.hitZ = hitZ;
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
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public int getSide() {
    return this.side;
  }
  
  public float getHitX() {
    return this.hitX;
  }
  
  public float getHitY() {
    return this.hitY;
  }
  
  public float getHitZ() {
    return this.hitZ;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\event\BlockAnvilActivatedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */