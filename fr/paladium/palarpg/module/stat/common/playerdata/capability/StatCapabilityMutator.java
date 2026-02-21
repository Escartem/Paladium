package fr.paladium.palarpg.module.stat.common.playerdata.capability;

import lombok.NonNull;

public class StatCapabilityMutator<T> {
  private boolean saved;
  
  private String id;
  
  private T value;
  
  private StatMutationType mutationType;
  
  public String toString() {
    return "StatCapabilityMutator(saved=" + isSaved() + ", id=" + getId() + ", value=" + getValue() + ", mutationType=" + getMutationType() + ")";
  }
  
  protected StatCapabilityMutator() {
    this.saved = true;
    this.mutationType = StatMutationType.ADDITIVE;
  }
  
  protected StatCapabilityMutator(boolean saved, String id, T value, StatMutationType mutationType) {
    this.saved = true;
    this.mutationType = StatMutationType.ADDITIVE;
    this.saved = saved;
    this.id = id;
    this.value = value;
    this.mutationType = mutationType;
  }
  
  public boolean isSaved() {
    return this.saved;
  }
  
  public String getId() {
    return this.id;
  }
  
  public T getValue() {
    return this.value;
  }
  
  public StatMutationType getMutationType() {
    return this.mutationType;
  }
  
  public static <T> StatCapabilityMutator<T> create() {
    return new StatCapabilityMutator<>();
  }
  
  public StatCapabilityMutator<T> setSaved(boolean saved) {
    this.saved = saved;
    return this;
  }
  
  public StatCapabilityMutator<T> setId(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    this.id = id;
    return this;
  }
  
  public StatCapabilityMutator<T> setValue(@NonNull T value) {
    if (value == null)
      throw new NullPointerException("value is marked non-null but is null"); 
    this.value = value;
    return this;
  }
  
  public StatCapabilityMutator<T> setMutationType(@NonNull StatMutationType mutationType) {
    if (mutationType == null)
      throw new NullPointerException("mutationType is marked non-null but is null"); 
    this.mutationType = mutationType;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\common\playerdata\capability\StatCapabilityMutator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */