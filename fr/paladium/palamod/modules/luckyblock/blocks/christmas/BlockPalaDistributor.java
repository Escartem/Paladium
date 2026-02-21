package fr.paladium.palamod.modules.luckyblock.blocks.christmas;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.christmas.TileEntityPalaDistributor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPalaDistributor extends Block {
  public BlockPalaDistributor() {
    super(Material.field_151576_e);
    func_149647_a(PLuckyBlock.TAB);
    func_149663_c("pala_distributor");
    func_149658_d("minecraft:stone");
    func_149711_c(2.0F);
    func_149672_a(field_149777_j);
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityPalaDistributor();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\christmas\BlockPalaDistributor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */