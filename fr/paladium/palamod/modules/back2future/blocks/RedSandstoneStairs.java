package fr.paladium.palamod.modules.back2future.blocks;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.block.BlockStairs;

public class RedSandstoneStairs extends BlockStairs implements IConfigurable {
  public RedSandstoneStairs() {
    super(ModBlocks.red_sandstone, 0);
    func_149711_c(0.8F);
    func_149713_g(0);
    func_149663_c(Utils.getUnlocalisedName("red_sandstone_stairs"));
    func_149647_a(Back2Future.enableRedSandstone ? Back2Future.creativeTab : null);
  }
  
  public boolean isEnabled() {
    return Back2Future.enableRedSandstone;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\RedSandstoneStairs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */