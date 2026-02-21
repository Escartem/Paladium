package fr.paladium.palamod.modules.paladium.common.blocks;

import fr.paladium.palamod.modules.paladium.common.logics.TileEntityAncientHammerBlockCorrupted;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCorrupted extends Block implements ITileEntityProvider {
  public BlockCorrupted() {
    super(Material.field_151576_e);
    func_149663_c("ancient_hammer_corrupted");
    func_149658_d("palamod:ancient_hammer/corrupted");
    func_149722_s();
    func_149752_b(6000000.0F);
    func_149647_a(CreativeTabs.field_78030_b);
  }
  
  public TileEntity func_149915_a(World world, int p_149915_2_) {
    return (TileEntity)new TileEntityAncientHammerBlockCorrupted();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\BlockCorrupted.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */