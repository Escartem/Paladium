package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemBalaclavaHelmet extends ItemArmor implements ITooltipWiki {
  public ItemBalaclavaHelmet() {
    super(ItemArmor.ArmorMaterial.IRON, 0, 0);
    func_77637_a(PLuckyBlock.TAB);
    func_77655_b("balaclava_helmet");
    func_111206_d("palamod:balaclava_helmet");
  }
  
  public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
    return "palamod:textures/models/balaclava_helmet.png";
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    if (repair.func_77973_b() == Items.field_151042_j)
      return true; 
    return false;
  }
  
  public int getType() {
    return 5;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#2.-lucky-block-en-findium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemBalaclavaHelmet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */