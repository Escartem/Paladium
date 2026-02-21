package fr.paladium.palamod.modules.luckyblock.blocks.supercraftingtable;

import java.util.Comparator;
import net.minecraft.item.crafting.IRecipe;

class null implements Comparator {
  private static final String __OBFID = "CL_00000091";
  
  public int compare(IRecipe p_compare_1_, IRecipe p_compare_2_) {
    return (p_compare_1_ instanceof net.minecraft.item.crafting.ShapelessRecipes && p_compare_2_ instanceof net.minecraft.item.crafting.ShapedRecipes) ? 1 : ((p_compare_2_ instanceof net.minecraft.item.crafting.ShapelessRecipes && p_compare_1_ instanceof net.minecraft.item.crafting.ShapedRecipes) ? -1 : (
      
      (p_compare_2_.func_77570_a() < p_compare_1_.func_77570_a()) ? -1 : (
      (p_compare_2_.func_77570_a() > p_compare_1_.func_77570_a()) ? 1 : 0)));
  }
  
  public int compare(Object p_compare_1_, Object p_compare_2_) {
    return compare((IRecipe)p_compare_1_, (IRecipe)p_compare_2_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\supercraftingtable\SuperCraftingManager$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */