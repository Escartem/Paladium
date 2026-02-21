package fr.paladium.palaspawner.common.spawner;

public class MobData {
  private String entityType;
  
  private int amount;
  
  protected MobData(String entityType, int amount) {
    this.entityType = entityType;
    this.amount = amount;
  }
  
  public String getEntityType() {
    return this.entityType;
  }
  
  public int getAmount() {
    return this.amount;
  }
  
  public static MobData of(String entityName) {
    return new MobData(entityName, 1);
  }
  
  public static MobData of(String entityName, int count) {
    return new MobData(entityName, count);
  }
  
  public void increment() {
    this.amount++;
  }
  
  public void increment(int amount) {
    this.amount += amount;
  }
  
  public void decrement() {
    this.amount--;
  }
  
  public void decrement(int amount) {
    this.amount -= amount;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\spawner\MobData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */