package fr.paladium.palaspawner.common.spawner.blueprint;

import fr.paladium.palaspawner.common.tile.Tier;

public class SpawnerData {
  private final String entityId;
  
  private final Tier tier;
  
  private int amount;
  
  public String toString() {
    return "SpawnerData(entityId=" + getEntityId() + ", tier=" + getTier() + ", amount=" + getAmount() + ")";
  }
  
  public String getEntityId() {
    return this.entityId;
  }
  
  public Tier getTier() {
    return this.tier;
  }
  
  public int getAmount() {
    return this.amount;
  }
  
  protected SpawnerData(String entityName, Tier tier) {
    this.entityId = entityName;
    this.amount = 1;
    this.tier = tier;
  }
  
  public static SpawnerData of(String entityName, Tier tier) {
    return new SpawnerData(entityName, tier);
  }
  
  public void increment() {
    this.amount++;
  }
  
  public void decrement() {
    this.amount--;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\spawner\blueprint\SpawnerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */