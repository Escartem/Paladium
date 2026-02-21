package fr.paladium.palamod.modules.back2future.blocks;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGravel;
import net.minecraft.item.Item;

public class OldGravel extends BlockGravel implements IConfigurable {
  public OldGravel() {
    func_149711_c(0.6F);
    func_149672_a(field_149767_g);
    func_149658_d("old_gravel");
    func_149663_c(Utils.getUnlocalisedName("old_gravel"));
    func_149647_a(Back2Future.enableOldGravel ? Back2Future.creativeTab : null);
  }
  
  public Item func_149650_a(int meta, Random rand, int fortune) {
    return Item.func_150898_a((Block)this);
  }
  
  public boolean isEnabled() {
    return Back2Future.enableOldGravel;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\OldGravel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */