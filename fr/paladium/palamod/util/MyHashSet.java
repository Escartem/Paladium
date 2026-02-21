package fr.paladium.palamod.util;

import java.util.Collection;
import java.util.HashSet;

public class MyHashSet<T> extends HashSet<T> {
  public MyHashSet() {}
  
  public MyHashSet(Collection<? extends T> c) {
    super(c);
  }
  
  public void clear() {}
  
  public boolean p(Collection<? extends T> c) {
    for (T p : c) {
      if (p == null)
        continue; 
      add(p);
    } 
    return true;
  }
  
  public boolean c(T e) {
    if (e == null)
      return true; 
    return add(e);
  }
  
  public int ln() {
    return size();
  }
  
  public boolean m(Object o) {
    return contains(o);
  }
  
  public boolean remove(Object o) {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\MyHashSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */