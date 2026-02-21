package fr.paladium.palamod.util;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class BlockUtils {
  public static void setBlockToAir(World world, int x, int y, int z) {
    setBlock(world, x, y, z, Blocks.field_150350_a, 0, 3);
  }
  
  public static void setBlock(World world, int x, int y, int z, Block block, int meta, int updateFlag) {
    Chunk chunk = world.func_72964_e(x >> 4, z >> 4);
    Block oldBlock = null;
    if ((updateFlag & 0x1) != 0)
      oldBlock = chunk.func_150810_a(x & 0xF, y, z & 0xF); 
    boolean flag = chunk.func_150807_a(x & 0xF, y, z & 0xF, block, meta);
    world.field_72984_F.func_76320_a("checkLight");
    world.func_147451_t(x, y, z);
    world.field_72984_F.func_76319_b();
    if (flag)
      world.markAndNotifyBlock(x, y, z, chunk, oldBlock, block, updateFlag); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\BlockUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */