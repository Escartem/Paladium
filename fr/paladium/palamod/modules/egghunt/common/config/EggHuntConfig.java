package fr.paladium.palamod.modules.egghunt.common.config;

import fr.paladium.palamod.modules.egghunt.utils.EggHuntLocation;

public class EggHuntConfig {
  private String bossName;
  
  private EggHuntLocation dragonPosition;
  
  private EggHuntLocation eggPosition;
  
  public void setBossName(String bossName) {
    this.bossName = bossName;
  }
  
  public void setDragonPosition(EggHuntLocation dragonPosition) {
    this.dragonPosition = dragonPosition;
  }
  
  public void setEggPosition(EggHuntLocation eggPosition) {
    this.eggPosition = eggPosition;
  }
  
  public String getBossName() {
    return this.bossName;
  }
  
  public EggHuntLocation getDragonPosition() {
    return this.dragonPosition;
  }
  
  public EggHuntLocation getEggPosition() {
    return this.eggPosition;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\common\config\EggHuntConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */