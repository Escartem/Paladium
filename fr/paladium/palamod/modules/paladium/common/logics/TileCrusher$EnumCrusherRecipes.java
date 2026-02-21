package fr.paladium.palamod.modules.paladium.common.logics;

import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.item.Item;

public enum EnumCrusherRecipes {
  ENDIUM(ItemsRegister.PALAMIXEDCOAL, ItemsRegister.FRUIT_ORANGEBLUE, 128),
  PALADIUM(ItemsRegister.TITANEMIXEDCOAL, ItemsRegister.FRUIT_KIWANO, 64),
  TITANE(ItemsRegister.AMETHYSTMIXEDCOAL, ItemsRegister.FRUIT_CHERVIL, 32),
  AMETHYST(ItemsRegister.GOLDMIXEDCOAL, ItemsRegister.FRUIT_EGGPLANT, 32);
  
  private final Item fuel;
  
  private final Item fruit;
  
  private final int barSize;
  
  EnumCrusherRecipes(Item fuel, Item fruit, int barSize) {
    this.fuel = fuel;
    this.fruit = fruit;
    this.barSize = barSize;
  }
  
  public Item getFuel() {
    return this.fuel;
  }
  
  public Item getFruit() {
    return this.fruit;
  }
  
  public int getBarSize() {
    return this.barSize;
  }
  
  public TileCrusher.EnumCrusherResult getResult() {
    return TileCrusher.EnumCrusherResult.values()[ordinal() + 1];
  }
  
  public EnumCrusherRecipes next() {
    if (ordinal() + 1 >= (values()).length)
      return values()[0]; 
    return values()[ordinal() + 1];
  }
  
  public static EnumCrusherRecipes findCurrentRecipe(Item fruit) {
    for (EnumCrusherRecipes r : values()) {
      if (r.getFruit() == fruit)
        return r; 
    } 
    return null;
  }
  
  public static EnumCrusherRecipes findRecipeFromFuel(Item fuel) {
    for (EnumCrusherRecipes r : values()) {
      if (r.getFuel() == fuel)
        return r; 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\logics\TileCrusher$EnumCrusherRecipes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */