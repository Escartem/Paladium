package fr.paladium.palamod.modules.miner.utils;

import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import net.minecraft.block.Block;

public class BlockData {
  private final Block block;
  
  private final int meta;
  
  private final BlockPos pos;
  
  public Block getBlock() {
    return this.block;
  }
  
  public int getMeta() {
    return this.meta;
  }
  
  public BlockPos getPos() {
    return this.pos;
  }
  
  public BlockData(Block block, BlockPos pos, int meta) {
    this.block = block;
    this.pos = pos;
    this.meta = meta;
  }
  
  public BlockData(Block block, int x, int y, int z, int meta) {
    this.block = block;
    this.pos = new BlockPos(x, y, z);
    this.meta = meta;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mine\\utils\BlockData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */