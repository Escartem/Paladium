package fr.paladium.palaforgeutils.lib.monitor;

import java.util.HashSet;
import java.util.Set;

public abstract class Monitor<T> implements IMonitor<T> {
  private volatile T value;
  
  private final Set<MonitorSubscriber<T>> eventSet;
  
  public Monitor() {
    this(null);
  }
  
  public Monitor(T value) {
    this.value = value;
    this.eventSet = new HashSet<>();
  }
  
  public T get() {
    return this.value;
  }
  
  public void set(T value) {
    this.value = value;
    publish();
  }
  
  public void subscribe(MonitorSubscriber<T> subscriber) {
    this.eventSet.add(subscriber);
  }
  
  public void unsubscribe(MonitorSubscriber<T> subscriber) {
    this.eventSet.remove(subscriber);
  }
  
  public void publish() {
    Set<MonitorSubscriber<T>> outdatedSet = new HashSet<>();
    for (MonitorSubscriber<T> subscriber : this.eventSet) {
      if (!subscriber.update(this.value))
        outdatedSet.add(subscriber); 
    } 
    this.eventSet.removeAll(outdatedSet);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\monitor\Monitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */