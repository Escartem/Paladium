package fr.paladium.palamod.modules.miner.utils;

import net.minecraft.item.ItemStack;

public class SubAutoCrafterRecipe {
  private ItemStack item;
  
  public void setItem(ItemStack item) {
    this.item = item;
  }
  
  public void setRessources(ItemStack[] ressources) {
    this.ressources = ressources;
  }
  
  public ItemStack getItem() {
    return this.item;
  }
  
  private ItemStack[] ressources = null;
  
  public ItemStack[] getRessources() {
    return this.ressources;
  }
  
  public SubAutoCrafterRecipe(ItemStack item) {
    this.item = item;
  }
  
  public SubAutoCrafterRecipe(ItemStack item, ItemStack... ressources) {
    this.item = item;
    this.ressources = ressources;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mine\\utils\SubAutoCrafterRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */