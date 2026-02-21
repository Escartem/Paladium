package fr.paladium.palamod.modules.paladium.common.blocks;

import fr.paladium.palamod.modules.paladium.common.logics.AmethystChestLogic;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAmethystChest extends BlockModdedChest {
  protected String field_149770_b;
  
  public BlockAmethystChest(String unlocalizedName) {
    super(Material.field_151573_f, 12.0F, "amethyst/amethyst_block", 8.0F);
    this.field_149770_b = unlocalizedName;
    func_149663_c(this.field_149770_b);
  }
  
  public TileEntity func_149915_a(World world, int metadata) {
    return (TileEntity)new AmethystChestLogic();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\blocks\BlockAmethystChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */