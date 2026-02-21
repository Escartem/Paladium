package fr.paladium.palamod.modules.luckyblock.items.easter;

import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.events.easter.RainbowSheepListener;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemRainbowColoring extends Item implements ITooltipInformations {
  public static final String NAME = "rainbow_coloring";
  
  public static final String RAINBOW_NAME_TAG = "jeb_";
  
  private static final String DESCRIPTION = "Quand il est utilisé sur un mouton celui-ci devient un mouton arc-en-ciel";
  
  public ItemRainbowColoring() {
    func_77655_b("rainbow_coloring");
    func_111206_d("palamod:rainbow_coloring");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(1);
  }
  
  public boolean func_111207_a(ItemStack stack, EntityPlayer player, EntityLivingBase entity) {
    if (entity instanceof EntitySheep) {
      EntitySheep sheep = (EntitySheep)entity;
      if (!player.field_70170_p.field_72995_K) {
        sheep.func_94058_c("jeb_");
        MonthlyUtils.sendMessage(player, new String[] { "§aLe mouton est maintenant arc-en-ciel !" });
        RainbowSheepListener.SHEEP_SET.add(sheep.func_110124_au());
      } 
      MonthlyUtils.decrementCurrentItem(player, stack);
      return true;
    } 
    return false;
  }
  
  public String[] getInformations(ItemStack itemStack, EntityPlayer entityPlayer, boolean b) {
    return MonthlyUtils.splitDescription("Quand il est utilisé sur un mouton celui-ci devient un mouton arc-en-ciel");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\easter\ItemRainbowColoring.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */