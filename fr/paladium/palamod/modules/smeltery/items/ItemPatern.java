package fr.paladium.palamod.modules.smeltery.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemPatern extends Item implements ITooltipWiki {
  public static int SOCKET = -1;
  
  public static int BROADSWORD = 0;
  
  public static int FASTSWORD = 1;
  
  public static int SWORD = 2;
  
  public static int PICKAXE = 3;
  
  public static int HAMMER = 4;
  
  public static int SHOVEL = 5;
  
  public static int AXE = 6;
  
  public static int INGOT = 7;
  
  public static final int BLOCK = 8;
  
  int type;
  
  public ItemPatern(String name, String texture, int type) {
    func_77655_b(name);
    func_111206_d("palamod:patern/" + texture);
    func_77637_a((CreativeTabs)Registry.SMELTERY_TAB);
    this.type = type;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-machines#8.-grinder";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\items\ItemPatern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */