package fr.paladium.palamod.modules.back2future.blocks;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.block.material.Material;

public class RedSandstoneSlab extends GenericSlab {
  public RedSandstoneSlab() {
    super(Material.field_151576_e, ModBlocks.red_sandstone);
    func_149752_b(30.0F);
    func_149711_c(2.0F);
    func_149663_c(Utils.getUnlocalisedName("red_sandstone_slab"));
    func_149647_a(Back2Future.enableRedSandstone ? Back2Future.creativeTab : null);
  }
  
  public boolean isEnabled() {
    return Back2Future.enableRedSandstone;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\RedSandstoneSlab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */