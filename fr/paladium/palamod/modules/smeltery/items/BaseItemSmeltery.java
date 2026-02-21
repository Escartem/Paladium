package fr.paladium.palamod.modules.smeltery.items;

import fr.paladium.palamod.common.Registry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BaseItemSmeltery extends Item {
  public BaseItemSmeltery(String unlocalizedName, String texture) {
    func_111206_d("palamod:" + texture);
    func_77637_a((CreativeTabs)Registry.PLANTS_TAB);
    func_77655_b(unlocalizedName);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\items\BaseItemSmeltery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */