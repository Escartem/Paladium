package fr.paladium.palamod.modules.back2future.items;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.item.ItemFood;

public class MuttonRaw extends ItemFood {
  public MuttonRaw() {
    super(2, 0.3F, true);
    func_111206_d("mutton_raw");
    func_77655_b(Utils.getUnlocalisedName("mutton_raw"));
    func_77637_a(Back2Future.enableMutton ? Back2Future.creativeTab : null);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\MuttonRaw.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */