package fr.paladium.palaforgeutils.lib.monitor;

@FunctionalInterface
public interface MonitorSubscriber<T> {
  boolean update(T paramT);
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\monitor\MonitorSubscriber.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */