package fr.paladium.palarpg.module.dungeon.common.network.chest;

import java.util.List;

public class CSPacketRPGDungeonChestValidateData {
  private int chestX;
  
  private int chestY;
  
  private int chestZ;
  
  private List<String> deletedItems;
  
  public CSPacketRPGDungeonChestValidateData() {}
  
  public CSPacketRPGDungeonChestValidateData(int chestX, int chestY, int chestZ, List<String> deletedItems) {
    this.chestX = chestX;
    this.chestY = chestY;
    this.chestZ = chestZ;
    this.deletedItems = deletedItems;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\chest\CSPacketRPGDungeonChestValidate$CSPacketRPGDungeonChestValidateData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */