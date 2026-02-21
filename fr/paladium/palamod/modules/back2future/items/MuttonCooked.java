package fr.paladium.palamod.modules.back2future.items;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.item.ItemFood;

public class MuttonCooked extends ItemFood implements IConfigurable {
  public MuttonCooked() {
    super(6, 0.8F, true);
    func_111206_d("mutton_cooked");
    func_77655_b(Utils.getUnlocalisedName("mutton_cooked"));
    func_77637_a(Back2Future.enableMutton ? Back2Future.creativeTab : null);
  }
  
  public boolean isEnabled() {
    return Back2Future.enableMutton;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\MuttonCooked.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */