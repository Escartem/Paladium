package fr.paladium.palamod.modules.paladium.common.logics;

import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.item.Item;

public enum EnumCrusherResult {
  NONE(null, 0),
  ENDIUM(ItemsRegister.ENDIUM_NUGGET, 1),
  PALADIUM(ItemsRegister.PALADIUM_INGOT, 15),
  TITANE(ItemsRegister.TITANE_INGOT, 15),
  AMETHYST(ItemsRegister.AMETHYST_INGOT, 5);
  
  private final Item item;
  
  private final int maxRate;
  
  EnumCrusherResult(Item item, int maxRate) {
    this.item = item;
    this.maxRate = maxRate;
  }
  
  public Item getItem() {
    return this.item;
  }
  
  public int getBarSize() {
    return (this == NONE) ? 0 : TileCrusher.EnumCrusherRecipes.values()[ordinal() - 1].getBarSize();
  }
  
  public int getMaxRate() {
    return this.maxRate;
  }
  
  public static EnumCrusherResult findByItem(Item item) {
    for (EnumCrusherResult r : values()) {
      if (r.getItem() == item)
        return r; 
    } 
    return null;
  }
  
  public EnumCrusherResult next() {
    if (ordinal() + 1 >= (values()).length)
      return values()[0]; 
    return values()[ordinal() + 1];
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\logics\TileCrusher$EnumCrusherResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */