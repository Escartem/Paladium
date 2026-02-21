package fr.paladium.palamod.modules.alchimiste.common.utils.impl;

import java.util.List;
import net.minecraft.item.ItemStack;

public class CauldronRecipe {
  private final ItemStack output;
  
  private final List<ItemStack> inputs;
  
  public ItemStack getOutput() {
    return this.output;
  }
  
  public List<ItemStack> getInputs() {
    return this.inputs;
  }
  
  public CauldronRecipe(ItemStack output, List<ItemStack> inputs) {
    this.output = output;
    this.inputs = inputs;
  }
  
  public int getOutputSize() {
    return this.output.field_77994_a;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\commo\\utils\impl\CauldronRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */