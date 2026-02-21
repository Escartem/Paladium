package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.paladium.common.items.weapons.BaseItemSword;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class ItemCouacSword extends BaseItemSword implements ITooltipWiki {
  public ItemCouacSword() {
    super(PToolMaterial.paladium, "couac_sword", ItemsRegister.PALADIUM_INGOT);
    func_77655_b("couac_sword");
    func_77637_a(PLuckyBlock.TAB);
  }
  
  public boolean func_77644_a(ItemStack item, EntityLivingBase entitybase, EntityLivingBase entitybase2) {
    entitybase.func_85030_a("palamod:chicken", 1.0F, 1.0F);
    return super.func_77644_a(item, entitybase, entitybase2);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#2.-lucky-block-en-findium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemCouacSword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */