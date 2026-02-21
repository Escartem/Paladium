package fr.paladium.palashop.common.provider.event;

import lombok.NonNull;

public abstract class ProviderEvent<T> {
  private final Phase phase;
  
  private boolean canceled;
  
  private T result;
  
  private boolean global;
  
  public void setResult(T result) {
    this.result = result;
  }
  
  public void setGlobal(boolean global) {
    this.global = global;
  }
  
  public String toString() {
    return "ProviderEvent(phase=" + getPhase() + ", canceled=" + isCanceled() + ", result=" + getResult() + ", global=" + isGlobal() + ")";
  }
  
  public ProviderEvent(Phase phase, boolean canceled, T result, boolean global) {
    this.phase = phase;
    this.canceled = canceled;
    this.result = result;
    this.global = global;
  }
  
  public ProviderEvent(Phase phase) {
    this.phase = phase;
  }
  
  public Phase getPhase() {
    return this.phase;
  }
  
  public boolean isCanceled() {
    return this.canceled;
  }
  
  public T getResult() {
    return this.result;
  }
  
  public boolean isGlobal() {
    return this.global;
  }
  
  public ProviderEvent(@NonNull Phase phase, T result) {
    if (phase == null)
      throw new NullPointerException("phase is marked non-null but is null"); 
    this.phase = phase;
    this.result = result;
  }
  
  public void setCanceled(boolean canceled) {
    if (!this.phase.isCancelable())
      throw new IllegalStateException("Cannot cancel action in phase " + this.phase); 
    this.canceled = canceled;
  }
  
  public enum Phase {
    PRE, POST;
    
    public boolean isCancelable() {
      return (this == PRE);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\provider\event\ProviderEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */