package fr.paladium.pet.server.skill.handler.impl.active.data;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class BlockTickable {
  private int x;
  
  private int y;
  
  private int z;
  
  private Block block;
  
  private long pasteTime;
  
  public BlockTickable(int x, int y, int z, Block block, long pasteTime) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.block = block;
    this.pasteTime = pasteTime;
  }
  
  public boolean paste(World world, long now) {
    if (now < this.pasteTime)
      return false; 
    world.func_147449_b(this.x, this.y, this.z, this.block);
    return true;
  }
  
  public static BlockTickable of(int x, int y, int z, Block block, long pasteTime) {
    return new BlockTickable(x, y, z, block, pasteTime);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\handler\impl\active\data\BlockTickable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */