package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.paladium.common.items.ItemBackpack;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemSuitcase extends ItemBackpack implements ITooltipInformations {
  public static final String NAME = "suitcase";
  
  public static final String DESCRIPTION = "Permet de stocker des objets sur 27 emplacements";
  
  public ItemSuitcase() {
    func_77625_d(1);
    func_77655_b("suitcase");
    func_77637_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_111206_d("palamod:suitcase");
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Permet de stocker des objets sur 27 emplacements");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\items\ItemSuitcase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */