package fr.paladium.palamod.modules.back2future.blocks;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import fr.paladium.palamod.modules.back2future.items.block.ItemBlockRedSandstone;
import net.minecraft.block.BlockSandStone;
import net.minecraft.item.ItemBlock;

public class RedSandstone extends BlockSandStone implements ModBlocks.ISubBlocksBlock, IConfigurable {
  public RedSandstone() {
    func_149711_c(0.8F);
    func_149658_d("red_sandstone");
    func_149663_c(Utils.getUnlocalisedName("red_sandstone"));
    func_149647_a(Back2Future.enableRedSandstone ? Back2Future.creativeTab : null);
  }
  
  public Class<? extends ItemBlock> getItemBlockClass() {
    return (Class)ItemBlockRedSandstone.class;
  }
  
  public boolean isEnabled() {
    return Back2Future.enableRedSandstone;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\RedSandstone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */