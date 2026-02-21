package fr.paladium.palamod.modules.back2future.items;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.item.Item;

public class PrismarineCrystals extends Item implements IConfigurable {
  public PrismarineCrystals() {
    func_111206_d("prismarine_crystals");
    func_77655_b(Utils.getUnlocalisedName("prismarine_crystals"));
    func_77637_a(Back2Future.enablePrismarine ? Back2Future.creativeTab : null);
  }
  
  public boolean isEnabled() {
    return Back2Future.enablePrismarine;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\PrismarineCrystals.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */