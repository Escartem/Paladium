package fr.paladium.palamod.modules.luckyblock.structures.blocks;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockExpirableBedrock extends Block implements IExpirableBlock {
  public BlockExpirableBedrock() {
    super(Material.field_151576_e);
    func_149722_s();
    func_149752_b(6000000.0F);
    func_149649_H();
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("expirable_bedrock");
    func_149658_d("bedrock");
  }
  
  public boolean hasTileEntity(int metadata) {
    return hasTile(metadata);
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return createTile(world, metadata);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\structures\blocks\BlockExpirableBedrock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */