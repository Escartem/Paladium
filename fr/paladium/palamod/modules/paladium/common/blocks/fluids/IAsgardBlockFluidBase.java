package fr.paladium.palamod.modules.paladium.common.blocks.fluids;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public interface IAsgardBlockFluidBase {
  default int canDisplacePre(Block block, IBlockAccess world, int x, int y, int z) {
    return 0;
  }
  
  default int displaceIfPossiblePre(Block block, World world, int x, int y, int z) {
    return 0;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\fluids\IAsgardBlockFluidBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */