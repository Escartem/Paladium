package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.item.luckystats;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemPersonalThruster extends Item implements ITooltipInformations {
  public static final String NAME = "personal_thruster";
  
  public static final String TEXTURE = "palamod:personal_thruster";
  
  private static String DESCRIPTION = "Si tu tiens cet objet et que tu cliques sur un autre joueur il sera propulsé dans les airs jusqu’à atteindre la hauteur 300 ou jusqu’à ce qu’il touche une surface.";
  
  public ItemPersonalThruster() {
    func_77625_d(1);
    func_77655_b("personal_thruster");
    func_111206_d("palamod:personal_thruster");
    func_77637_a(PLuckyBlock.TAB);
    func_77656_e(2);
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription(DESCRIPTION);
  }
  
  public boolean func_77616_k(ItemStack stack) {
    return false;
  }
  
  public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\item\luckystats\ItemPersonalThruster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */