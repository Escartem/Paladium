package fr.paladium.palamod.modules.back2future.items;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class DragonBreath extends Item implements IConfigurable {
  public DragonBreath() {
    func_77631_c("-14+13");
    func_111206_d("dragon_breath");
    func_77642_a(Items.field_151069_bo);
    func_77655_b(Utils.getUnlocalisedName("dragon_breath"));
    func_77637_a(Back2Future.enableLingeringPotions ? Back2Future.creativeTab : null);
  }
  
  public boolean isEnabled() {
    return Back2Future.enableLingeringPotions;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\DragonBreath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */