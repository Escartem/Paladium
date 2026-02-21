package fr.paladium.palamod.common.items;

import fr.paladium.palamod.common.Registry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BaseItemPlants extends Item {
  public BaseItemPlants(String texture) {
    func_111206_d("palamod:" + texture);
    func_77637_a((CreativeTabs)Registry.PLANTS_TAB);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\items\BaseItemPlants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */