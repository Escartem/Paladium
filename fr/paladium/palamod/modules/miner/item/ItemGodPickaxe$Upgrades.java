package fr.paladium.palamod.modules.miner.item;

public enum Upgrades {
  AUTO_SMELT(1),
  BIG_HOLE(1);
  
  private final int maxLevel;
  
  public int getMaxLevel() {
    return this.maxLevel;
  }
  
  Upgrades(int maxLevel) {
    this.maxLevel = maxLevel;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\item\ItemGodPickaxe$Upgrades.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */