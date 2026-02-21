package fr.paladium.palaforgeutils.lib.monitor;

public interface IMonitor<T> {
  T get();
  
  void set(T paramT);
  
  void subscribe(MonitorSubscriber<T> paramMonitorSubscriber);
  
  void unsubscribe(MonitorSubscriber<T> paramMonitorSubscriber);
  
  void publish();
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\monitor\IMonitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */