package fr.paladium.palamod.modules.paladium.common.logics.cobblebreaker;

import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.item.Item;

public enum CobbleBreakerUpgrade {
  DEFAULT(400, 0, null),
  AMETHYST(266, 9, ItemsRegister.COBBLEBREAKER_AMETHYST_UPGRADE),
  TITANE(160, 13, ItemsRegister.COBBLEBREAKER_TITANE_UPGRADE),
  PALADIUM(100, 16, ItemsRegister.COBBLEBREAKER_PALADIUM_UPGRADE);
  
  private int work;
  
  private int level;
  
  private Item item;
  
  public int getWork() {
    return this.work;
  }
  
  public int getLevel() {
    return this.level;
  }
  
  public Item getItem() {
    return this.item;
  }
  
  CobbleBreakerUpgrade(int work, int level, Item item) {
    this.work = work;
    this.level = level;
    this.item = item;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\logics\cobblebreaker\CobbleBreakerUpgrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */