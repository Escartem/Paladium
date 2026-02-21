package fr.paladium.palamod.modules.miner.init;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.miner.blocks.BlockMinerPlant;
import net.minecraft.block.Block;
import net.minecraft.world.World;

final class null extends BlockMinerPlant {
  null(String name) {
    super(name);
  }
  
  public boolean func_149854_a(Block block) {
    return (block == BlocksRegister.STRANGE_GRASS || block == BlocksRegister.STRANGE_DIRT || block == BlocksRegister.STRANGE_SAND);
  }
  
  public boolean func_149718_j(World world, int x, int y, int z) {
    Block block = world.func_147439_a(x, y - 1, z);
    return (block == BlocksRegister.STRANGE_GRASS || block == BlocksRegister.STRANGE_DIRT || block == BlocksRegister.STRANGE_SAND);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\init\Blocks$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */