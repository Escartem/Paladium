package fr.paladium.palamod.modules.luckyblock.blocks.may;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.may.TileEntityTimedBedrock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTimedBedrock extends Block {
  public BlockTimedBedrock() {
    super(Material.field_151576_e);
    func_149722_s();
    func_149752_b(6000000.0F);
    func_149649_H();
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("timed_bedrock");
    func_149658_d("bedrock");
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityTimedBedrock();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\may\BlockTimedBedrock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */