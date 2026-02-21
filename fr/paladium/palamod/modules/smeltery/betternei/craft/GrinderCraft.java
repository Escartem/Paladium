package fr.paladium.palamod.modules.smeltery.betternei.craft;

import fr.paladium.betternei.api.craft.Craft;

public class GrinderCraft extends Craft {
  private GrinderCraftType craftType;
  
  public void setCraftType(GrinderCraftType craftType) {
    this.craftType = craftType;
  }
  
  public void setPaladiumCost(int paladiumCost) {
    this.paladiumCost = paladiumCost;
  }
  
  public GrinderCraftType getCraftType() {
    return this.craftType;
  }
  
  private int paladiumCost = 0;
  
  public int getPaladiumCost() {
    return this.paladiumCost;
  }
  
  public enum GrinderCraftType {
    UPGRADE, CRAFTING, FILL_TANK;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\betternei\craft\GrinderCraft.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */