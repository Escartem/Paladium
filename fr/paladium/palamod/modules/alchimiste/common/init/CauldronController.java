package fr.paladium.palamod.modules.alchimiste.common.init;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.betternei.client.util.ItemUtils;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.CauldronRecipe;
import fr.paladium.tutorial.common.event.CauldronCraftEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class CauldronController {
  private final List<CauldronRecipe> RECIPES = new ArrayList<>();
  
  private final Map<String, ItemStack> GLUEBALLS_RECIPES = new HashMap<>();
  
  public void addRecipe(ItemStack output, ItemStack... inputs) {
    this.RECIPES.add(new CauldronRecipe(output.func_77946_l(), Arrays.asList(inputs)));
  }
  
  public void addGlueballRecipe(String seve1, String seve2, ItemStack output) {
    this.GLUEBALLS_RECIPES.put(seve1 + "," + seve2, output.func_77946_l());
  }
  
  public CauldronRecipe getOutputFor(EntityPlayer closest, ItemStack... inputs) {
    if (inputs.length == 0)
      return null; 
    for (CauldronRecipe recipe : this.RECIPES) {
      List<ItemStack> recipeInputs = recipe.getInputs();
      boolean valid = true;
      for (ItemStack stack : recipeInputs) {
        if (!containItem(Arrays.asList(inputs), stack)) {
          valid = false;
          break;
        } 
      } 
      if (valid) {
        if (closest != null && !closest.field_70170_p.field_72995_K)
          MinecraftForge.EVENT_BUS.post((Event)new CauldronCraftEvent(closest, recipe.getOutput())); 
        return recipe;
      } 
    } 
    return null;
  }
  
  private boolean containItem(List<ItemStack> list, ItemStack search) {
    boolean contained = false;
    for (ItemStack item : list) {
      if (ItemUtils.isEquals(item, search, true)) {
        contained = true;
        break;
      } 
    } 
    return contained;
  }
  
  public ItemStack getOutputFor(EntityPlayer closest, String seve1, String seve2) {
    if (this.GLUEBALLS_RECIPES.containsKey(seve1 + "," + seve2)) {
      ItemStack output = this.GLUEBALLS_RECIPES.get(seve1 + "," + seve2);
      if (closest != null && !closest.field_70170_p.field_72995_K)
        MinecraftForge.EVENT_BUS.post((Event)new CauldronCraftEvent(closest, output)); 
      return output;
    } 
    return null;
  }
  
  public List<CauldronRecipe> getRecipes() {
    return this.RECIPES;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\init\CauldronController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */