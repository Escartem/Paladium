package fr.paladium.palamod.modules.paladium.common.blocks;

import fr.paladium.palamod.modules.paladium.common.logics.GPaladiumChestLogic;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGPaladiumChest extends BlockModdedChest {
  protected String field_149770_b;
  
  public BlockGPaladiumChest(String unlocalizedName) {
    super(Material.field_151573_f, 12.0F, "gpaladium/gpaladium_block", 8.0F);
    this.field_149770_b = unlocalizedName;
    func_149663_c(this.field_149770_b);
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new GPaladiumChestLogic();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\BlockGPaladiumChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */