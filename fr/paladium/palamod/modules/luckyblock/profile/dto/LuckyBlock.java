package fr.paladium.palamod.modules.luckyblock.profile.dto;

public class LuckyBlock {
  private String name;
  
  private String unlockDate;
  
  public LuckyBlock() {}
  
  public LuckyBlock(String name, String unlockDate) {
    this.name = name;
    this.unlockDate = unlockDate;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getUnlockDate() {
    return this.unlockDate;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\profile\dto\LuckyBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */