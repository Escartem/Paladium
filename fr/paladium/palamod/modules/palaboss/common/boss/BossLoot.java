package fr.paladium.palamod.modules.palaboss.common.boss;

public class BossLoot {
  private String lootItemID;
  
  private int count;
  
  public BossLoot(String lootItemID, int count) {
    this.lootItemID = lootItemID;
    this.count = count;
  }
  
  public String getLootItemID() {
    return this.lootItemID;
  }
  
  public void setLootItemID(String lootItemID) {
    this.lootItemID = lootItemID;
  }
  
  public int getCount() {
    return this.count;
  }
  
  public void setCount(int count) {
    this.count = count;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\boss\BossLoot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */