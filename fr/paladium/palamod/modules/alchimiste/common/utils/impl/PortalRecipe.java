package fr.paladium.palamod.modules.alchimiste.common.utils.impl;

import net.minecraft.item.ItemStack;

public class PortalRecipe {
  private String seveRequired;
  
  private ItemStack dropStack;
  
  private int requiredFullSeve;
  
  public PortalRecipe(String seveRequired, ItemStack dropStack, int requiredFullSeve) {
    this.seveRequired = seveRequired;
    this.dropStack = dropStack;
    this.requiredFullSeve = requiredFullSeve;
  }
  
  public String getSeveRequired() {
    return this.seveRequired;
  }
  
  public ItemStack getDropStack() {
    return this.dropStack.func_77946_l();
  }
  
  public int getRequiredFullSeve() {
    return this.requiredFullSeve;
  }
  
  public String toString() {
    return "[seveRequired=" + this.seveRequired + ", dropStack=" + this.dropStack.toString() + ", requiredFullSeve=" + this.requiredFullSeve + "]";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\commo\\utils\impl\PortalRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */