package fr.paladium.palamod.modules.paladium.common.items;

import fr.paladium.palamod.common.Registry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

public class ItemSwordCosmetic extends ItemSword {
  private String name;
  
  public ItemSwordCosmetic(Item.ToolMaterial material, String name) {
    super(material);
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_77655_b(name);
    func_111206_d("palamod:" + name);
    this.name = name;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\ItemSwordCosmetic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */