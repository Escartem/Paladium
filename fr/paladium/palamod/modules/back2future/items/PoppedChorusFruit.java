package fr.paladium.palamod.modules.back2future.items;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.item.Item;

public class PoppedChorusFruit extends Item implements IConfigurable {
  public PoppedChorusFruit() {
    func_111206_d("chorus_fruit_popped");
    func_77655_b(Utils.getUnlocalisedName("chorus_fruit_popped"));
    func_77637_a(Back2Future.enableChorusFruit ? Back2Future.creativeTab : null);
  }
  
  public boolean isEnabled() {
    return Back2Future.enableChorusFruit;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\PoppedChorusFruit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */