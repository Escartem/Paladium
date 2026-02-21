package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.paladium.common.items.weapons.BaseItemSword;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemGhostlySword extends BaseItemSword implements ITooltipInformations {
  public static final String NAME = "ghostly_sword";
  
  public static final String DESCRIPTION = "Épée invisible pour les autres joueurs";
  
  public ItemGhostlySword() {
    super(PToolMaterial.paladium, "ghostly_sword", ItemsRegister.PALADIUM_INGOT);
    func_77655_b("ghostly_sword");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Épée invisible pour les autres joueurs");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\items\ItemGhostlySword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */