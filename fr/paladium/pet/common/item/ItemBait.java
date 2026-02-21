package fr.paladium.pet.common.item;

import fr.paladium.pet.client.tab.TabPet;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBait extends Item {
  public static final String NAME = "bait";
  
  public ItemBait() {
    func_77655_b("bait");
    func_111206_d("palapet:bait");
    func_77637_a((CreativeTabs)TabPet.INSTANCE);
    func_77625_d(1);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\item\ItemBait.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */