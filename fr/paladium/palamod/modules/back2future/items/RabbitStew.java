package fr.paladium.palamod.modules.back2future.items;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.item.ItemSoup;

public class RabbitStew extends ItemSoup implements IConfigurable {
  public RabbitStew() {
    super(10);
    func_111206_d("rabbit_stew");
    func_77655_b(Utils.getUnlocalisedName("rabbit_stew"));
    func_77637_a(Back2Future.enableRabbit ? Back2Future.creativeTab : null);
  }
  
  public boolean isEnabled() {
    return Back2Future.enableRabbit;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\RabbitStew.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */