package fr.paladium.palamod.modules.luckyblock.monthly.tickable;

import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;

public abstract class ATickable<T> extends ALuckyEvent {
  protected final T data;
  
  private final long tickDelayMillis;
  
  private long lastTickMillis;
  
  public T getData() {
    return this.data;
  }
  
  public long getTickDelayMillis() {
    return this.tickDelayMillis;
  }
  
  public long getLastTickMillis() {
    return this.lastTickMillis;
  }
  
  public ATickable(T data, long tickDelayMillis) {
    this.data = data;
    this.lastTickMillis = 0L;
    this.tickDelayMillis = tickDelayMillis;
  }
  
  private boolean canTick(long now) {
    return (now - this.lastTickMillis >= this.tickDelayMillis);
  }
  
  public void tick(long now) {
    if (!canTick(now))
      return; 
    this.lastTickMillis = now;
    onTick(now);
  }
  
  protected abstract void onTick(long paramLong);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\tickable\ATickable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */