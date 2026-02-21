package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.paladium.common.items.weapons.BaseItemSword;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemButcherKnife extends BaseItemSword implements ITooltipInformations {
  public static final String NAME = "butcher_knife";
  
  public static final String DESCRIPTION = "Similaire à une épée en Paladium. Inflige aux zombies 25 points de dégâts en plus. Ne fonctionne pas avec les enchantements Châtiments et Tranchant ";
  
  public ItemButcherKnife() {
    super(PToolMaterial.paladium, "butcher_knife", ItemsRegister.PALADIUM_INGOT);
    func_77655_b("butcher_knife");
    func_111206_d("palamod:butcher_knife");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public String[] getInformations(ItemStack stack, EntityPlayer player, boolean b) {
    return MonthlyUtils.splitDescription("Similaire à une épée en Paladium. Inflige aux zombies 25 points de dégâts en plus. Ne fonctionne pas avec les enchantements Châtiments et Tranchant ");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\items\ItemButcherKnife.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */