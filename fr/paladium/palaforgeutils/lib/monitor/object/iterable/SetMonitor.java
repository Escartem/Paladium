package fr.paladium.palaforgeutils.lib.monitor.object.iterable;

import fr.paladium.palaforgeutils.lib.monitor.Monitor;
import java.util.Set;

public class SetMonitor<E> extends Monitor<Set<E>> {
  public boolean add(E e) {
    boolean success = ((Set<E>)get()).add(e);
    publish();
    return success;
  }
  
  public void clear() {
    ((Set)get()).clear();
    publish();
  }
  
  public boolean contains(E e) {
    return ((Set)get()).contains(e);
  }
  
  public boolean isEmpty() {
    return ((Set)get()).isEmpty();
  }
  
  public boolean remove(E e) {
    boolean success = ((Set)get()).remove(e);
    publish();
    return success;
  }
  
  public int size() {
    return ((Set)get()).size();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\monitor\object\iterable\SetMonitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */