package fr.paladium.palamod.modules.back2future.blocks;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import java.util.Random;
import net.minecraft.block.BlockObsidian;
import net.minecraft.item.Item;

public class CryingObsidian extends BlockObsidian implements IConfigurable {
  public CryingObsidian() {
    func_149711_c(50.0F);
    func_149752_b(100.0F);
    func_149672_a(field_149780_i);
    setHarvestLevel("pickaxe", 3);
    func_149658_d("crying_obsidian");
    func_149663_c(Utils.getUnlocalisedName("crying_obsidian"));
    func_149647_a(Back2Future.enableCryingObsidian ? Back2Future.creativeTab : null);
  }
  
  public boolean isEnabled() {
    return Back2Future.enableCryingObsidian;
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return Item.func_150898_a(ModBlocks.crying_obsidian);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\CryingObsidian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */