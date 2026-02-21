package fr.paladium.palamod.modules.back2future.items;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.item.Item;

public class RabbitHide extends Item implements IConfigurable {
  public RabbitHide() {
    func_111206_d("rabbit_hide");
    func_77655_b(Utils.getUnlocalisedName("rabbit_hide"));
    func_77637_a(Back2Future.enableRabbit ? Back2Future.creativeTab : null);
  }
  
  public boolean isEnabled() {
    return Back2Future.enableRabbit;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\RabbitHide.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */