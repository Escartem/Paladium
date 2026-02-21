package fr.paladium.palamod.modules.egghunt.utils;

public class EggHuntStatus {
  private String eggOwner;
  
  private String dragonKiller;
  
  private long dragonDeathTime;
  
  private EggHuntLocation currentPosition;
  
  public void setEggOwner(String eggOwner) {
    this.eggOwner = eggOwner;
  }
  
  public void setDragonKiller(String dragonKiller) {
    this.dragonKiller = dragonKiller;
  }
  
  public void setDragonDeathTime(long dragonDeathTime) {
    this.dragonDeathTime = dragonDeathTime;
  }
  
  public void setCurrentPosition(EggHuntLocation currentPosition) {
    this.currentPosition = currentPosition;
  }
  
  public String getEggOwner() {
    return this.eggOwner;
  }
  
  public String getDragonKiller() {
    return this.dragonKiller;
  }
  
  public long getDragonDeathTime() {
    return this.dragonDeathTime;
  }
  
  public EggHuntLocation getCurrentPosition() {
    return this.currentPosition;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghun\\utils\EggHuntStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */