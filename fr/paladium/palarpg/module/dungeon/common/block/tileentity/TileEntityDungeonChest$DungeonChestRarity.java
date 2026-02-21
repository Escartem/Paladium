package fr.paladium.palarpg.module.dungeon.common.block.tileentity;

public enum DungeonChestRarity {
  COMMON("Commun"),
  RARE("Rare"),
  LEGENDARY("Légendaire");
  
  DungeonChestRarity(String name) {
    this.name = name;
  }
  
  private final String name;
  
  public String getName() {
    return this.name;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\block\tileentity\TileEntityDungeonChest$DungeonChestRarity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */