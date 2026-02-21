package fr.paladium.palamod.modules.luckyblock.items.easter;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemEasterTotem extends Item implements ITooltipInformations {
  private static final String NAME = "easter_totem";
  
  private static final String DESCRIPTION = "Quand un outil (pioche, pelle, hache et hammer) perd en durabilité c'est le totem qui prend des dégâts à la place.";
  
  private static final int DURABILITY = 10000;
  
  public ItemEasterTotem() {
    func_77655_b("easter_totem");
    func_111206_d("palamod:easter_totem");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
    func_77656_e(10000);
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Quand un outil (pioche, pelle, hache et hammer) perd en durabilité c'est le totem qui prend des dégâts à la place.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\easter\ItemEasterTotem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */