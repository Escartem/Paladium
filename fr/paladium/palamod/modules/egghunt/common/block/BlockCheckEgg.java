package fr.paladium.palamod.modules.egghunt.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCheckEgg extends BlockContainer {
  public BlockCheckEgg() {
    super(Material.field_151576_e);
    func_149752_b(6000000.0F);
  }
  
  public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
    return new TileCheckEgg();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\common\block\BlockCheckEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */