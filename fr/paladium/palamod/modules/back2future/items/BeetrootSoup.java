package fr.paladium.palamod.modules.back2future.items;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.IConfigurable;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import net.minecraft.init.Items;
import net.minecraft.item.ItemSoup;

public class BeetrootSoup extends ItemSoup implements IConfigurable {
  public BeetrootSoup() {
    super(6);
    func_77642_a(Items.field_151054_z);
    func_111206_d("beetroot_soup");
    func_77655_b(Utils.getUnlocalisedName("beetroot_soup"));
    func_77637_a(Back2Future.enableBeetroot ? Back2Future.creativeTab : null);
  }
  
  public boolean isEnabled() {
    return Back2Future.enableBeetroot;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\items\BeetrootSoup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */