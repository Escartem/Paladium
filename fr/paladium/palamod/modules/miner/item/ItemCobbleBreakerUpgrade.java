package fr.paladium.palamod.modules.miner.item;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.paladium.common.logics.cobblebreaker.CobbleBreakerUpgrade;
import net.minecraft.item.ItemStack;

public class ItemCobbleBreakerUpgrade extends ItemMiner implements ITooltipWiki {
  private final CobbleBreakerUpgrade type;
  
  public CobbleBreakerUpgrade getType() {
    return this.type;
  }
  
  public ItemCobbleBreakerUpgrade(String name, CobbleBreakerUpgrade type) {
    super(name);
    func_77625_d(16);
    this.type = type;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-machines#4.-cobblebreaker";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\item\ItemCobbleBreakerUpgrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */