package fr.paladium.palamod.modules.miner.utils;

import net.minecraft.item.ItemStack;

public class AutoCrafterRecipe {
  private String id;
  
  private ItemStack result;
  
  private SubAutoCrafterRecipe[] ressources;
  
  public void setId(String id) {
    this.id = id;
  }
  
  public void setResult(ItemStack result) {
    this.result = result;
  }
  
  public void setRessources(SubAutoCrafterRecipe[] ressources) {
    this.ressources = ressources;
  }
  
  public String getId() {
    return this.id;
  }
  
  public ItemStack getResult() {
    return this.result;
  }
  
  public SubAutoCrafterRecipe[] getRessources() {
    return this.ressources;
  }
  
  public AutoCrafterRecipe(String id, ItemStack result, SubAutoCrafterRecipe... ressources) {
    this.id = id;
    this.result = result;
    this.ressources = ressources;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mine\\utils\AutoCrafterRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */