package fr.paladium.palamod.modules.miner.item;

import fr.paladium.common.utils.ITooltipWiki;
import net.minecraft.item.ItemStack;

public class ItemGodPickaxeUpgrade extends ItemMiner implements ITooltipWiki {
  private final ItemGodPickaxe.Upgrades type;
  
  public ItemGodPickaxe.Upgrades getType() {
    return this.type;
  }
  
  public ItemGodPickaxeUpgrade(ItemGodPickaxe.Upgrades type) {
    super("god_pickaxe_" + type.toString().toLowerCase());
    this.type = type;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#6.-la-pickaxe-of-the-gods-pog";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\item\ItemGodPickaxeUpgrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */