package fr.paladium.palamod.modules.luckyblock.structures.blocks;

import fr.paladium.palamod.modules.luckyblock.structures.AbstractStructure;
import fr.paladium.palamod.modules.luckyblock.structures.tiles.TileEntityExpirableBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public interface IExpirableBlock {
  default boolean hasTile(int metadata) {
    return true;
  }
  
  default TileEntity createTile(World world, int metadata) {
    return (TileEntity)new TileEntityExpirableBlock();
  }
  
  default void init(World world, int x, int y, int z, long expirationMillis) {
    init(world, x, y, z, expirationMillis, null);
  }
  
  default void init(World world, int x, int y, int z, long expirationMillis, AbstractStructure structure) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (!(tileEntity instanceof TileEntityExpirableBlock))
      return; 
    TileEntityExpirableBlock tileEntityExpirableBlock = (TileEntityExpirableBlock)tileEntity;
    tileEntityExpirableBlock.init(expirationMillis, structure);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\structures\blocks\IExpirableBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */