package fr.paladium.common;

import fr.paladium.common.tooltip.ModuleItemTooltip;
import fr.paladium.common.utils.ITooltipInformations;
import fr.paladium.common.utils.ITooltipWiki;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TooltipManager {
  private static Map<ItemStack, String[]> informations = (Map)new HashMap<>();
  
  private static Map<ItemStack, String> wikis = new HashMap<>();
  
  public static void addTooltipInformation(ItemStack item, String... tooltip) {
    informations.put(item, tooltip);
  }
  
  public static void addWikiUrl(ItemStack item, String url) {
    wikis.put(item, url);
  }
  
  public static String[] getTooltipInformation(ItemStack item, EntityPlayer player, boolean showAdvancedItemTooltips) {
    if (!ModuleItemTooltip.getInstance().isShowDescription())
      return null; 
    if (item.func_77973_b() instanceof ITooltipInformations)
      return ((ITooltipInformations)item.func_77973_b()).getInformations(item, player, showAdvancedItemTooltips); 
    Block block = Block.func_149634_a(item.func_77973_b());
    if (block != null && block instanceof ITooltipInformations)
      return ((ITooltipInformations)block).getInformations(item, player, showAdvancedItemTooltips); 
    for (ItemStack i : informations.keySet()) {
      if (i.func_77969_a(item))
        return informations.get(i); 
    } 
    return null;
  }
  
  public static String getWikiURL(ItemStack item) {
    if (!ModuleItemTooltip.getInstance().isShowWiki())
      return null; 
    if (item.func_77973_b() instanceof ITooltipWiki)
      return ((ITooltipWiki)item.func_77973_b()).getUrl(item); 
    Block block = Block.func_149634_a(item.func_77973_b());
    if (block != null && block instanceof ITooltipWiki)
      return ((ITooltipWiki)block).getUrl(item); 
    for (ItemStack i : wikis.keySet()) {
      if (i.func_77969_a(item))
        return wikis.get(i); 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\common\TooltipManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */