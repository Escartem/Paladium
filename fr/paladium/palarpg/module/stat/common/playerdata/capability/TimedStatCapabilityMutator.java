package fr.paladium.palarpg.module.stat.common.playerdata.capability;

public class TimedStatCapabilityMutator<T> extends StatCapabilityMutator<T> {
  private int totalTick;
  
  private int tick;
  
  private Runnable callback;
  
  public String toString() {
    return "TimedStatCapabilityMutator(totalTick=" + getTotalTick() + ", tick=" + getTick() + ", callback=" + getCallback() + ")";
  }
  
  public int getTotalTick() {
    return this.totalTick;
  }
  
  public int getTick() {
    return this.tick;
  }
  
  public Runnable getCallback() {
    return this.callback;
  }
  
  public static <T> TimedStatCapabilityMutator<T> create() {
    return new TimedStatCapabilityMutator<>();
  }
  
  public TimedStatCapabilityMutator<T> setTotalTick(int tick) {
    this.totalTick = tick;
    return this;
  }
  
  public TimedStatCapabilityMutator<T> setTick(int tick) {
    this.tick = tick;
    this.totalTick = tick;
    return this;
  }
  
  public TimedStatCapabilityMutator<T> setCallback(Runnable callback) {
    this.callback = callback;
    return this;
  }
  
  public void executeCallback() {
    if (this.callback != null)
      this.callback.run(); 
  }
  
  public void decrementTick() {
    this.tick--;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\common\playerdata\capability\TimedStatCapabilityMutator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */