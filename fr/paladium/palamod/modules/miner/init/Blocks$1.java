package fr.paladium.palamod.modules.miner.init;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.miner.blocks.BlockMinerPlant;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

final class null extends BlockMinerPlant {
  null(String name, boolean growable, int growableMaxStated, float... heights) {
    super(name, growable, growableMaxStated, heights);
  }
  
  public boolean func_149854_a(Block block) {
    return (block == BlocksRegister.STRANGE_GRASS || block == BlocksRegister.STRANGE_DIRT || block == BlocksRegister.STRANGE_SAND);
  }
  
  public boolean func_149718_j(World world, int x, int y, int z) {
    Block block = world.func_147439_a(x, y - 1, z);
    return ((block == BlocksRegister.STRANGE_GRASS || block == BlocksRegister.STRANGE_DIRT || block == BlocksRegister.STRANGE_SAND) && (world.func_147439_a(x - 1, y - 1, z) == Blocks.field_150355_j || world.func_147439_a(x + 1, y - 1, z) == Blocks.field_150355_j || world.func_147439_a(x, y - 1, z - 1) == Blocks.field_150355_j || world.func_147439_a(x, y - 1, z + 1) == Blocks.field_150355_j));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\init\Blocks$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */