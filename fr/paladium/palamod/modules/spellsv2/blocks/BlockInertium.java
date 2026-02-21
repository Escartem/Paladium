package fr.paladium.palamod.modules.spellsv2.blocks;

import fr.paladium.palamod.modules.spellsv2.tile.TileEntityInertiumBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockInertium extends BlockContainer {
  public BlockInertium() {
    super(Material.field_151576_e);
    func_149722_s();
    func_149752_b(1000.0F);
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    TileEntityInertiumBlock tile = new TileEntityInertiumBlock();
    return (TileEntity)tile;
  }
  
  public IIcon func_149673_e(IBlockAccess ba, int x, int y, int z, int dir) {
    TileEntity tile = ba.func_147438_o(x, y, z);
    if (tile instanceof TileEntityInertiumBlock) {
      Block block = ((TileEntityInertiumBlock)tile).getBlock();
      if (block == null)
        block = Blocks.field_150348_b; 
      func_149663_c(block.func_149739_a().replaceFirst("tile.", ""));
      return block.func_149673_e(ba, x, y, z, dir);
    } 
    func_149663_c(Blocks.field_150348_b.func_149739_a().replaceFirst("tile.", ""));
    return Blocks.field_150348_b.func_149673_e(ba, x, y, z, dir);
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public int func_149701_w() {
    return 1;
  }
  
  public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\blocks\BlockInertium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */