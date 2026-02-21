package fr.paladium.palamod.modules.miner.item;

public class Upgrade {
  private final ItemGodPickaxe.Upgrades type;
  
  private final int level;
  
  public ItemGodPickaxe.Upgrades getType() {
    return this.type;
  }
  
  public int getLevel() {
    return this.level;
  }
  
  public Upgrade(ItemGodPickaxe.Upgrades type, int level) {
    this.type = type;
    this.level = level;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\item\ItemGodPickaxe$Upgrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */