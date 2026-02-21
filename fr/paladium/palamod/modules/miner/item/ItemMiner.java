package fr.paladium.palamod.modules.miner.item;

import fr.paladium.palamod.common.Registry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemMiner extends Item {
  public ItemMiner(String name) {
    func_77637_a((CreativeTabs)Registry.MINER_TAB);
    func_77655_b(name);
    func_111206_d("palamod:" + name);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\item\ItemMiner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */