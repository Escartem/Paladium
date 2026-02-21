package fr.paladium.palaforgeutils.lib.monitor.object.iterable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.monitor.Monitor;
import java.util.List;

public class ListMonitor<E> extends Monitor<List<E>> {
  public boolean add(E e) {
    boolean success = ((List<E>)get()).add(e);
    publish();
    return success;
  }
  
  public void clear() {
    ((List)get()).clear();
    publish();
  }
  
  public boolean contains(E e) {
    return ((List)get()).contains(e);
  }
  
  @SideOnly(Side.SERVER)
  public E get(int index) {
    return ((List<E>)get()).get(index);
  }
  
  public int indexOf(E e) {
    return ((List)get()).indexOf(e);
  }
  
  public boolean isEmpty() {
    return ((List)get()).isEmpty();
  }
  
  public boolean remove(E e) {
    boolean success = ((List)get()).remove(e);
    publish();
    return success;
  }
  
  public E remove(int index) {
    E result = ((List<E>)get()).remove(index);
    publish();
    return result;
  }
  
  public E set(int index, E element) {
    E result = ((List<E>)get()).set(index, element);
    publish();
    return result;
  }
  
  public int size() {
    return ((List)get()).size();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\monitor\object\iterable\ListMonitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */