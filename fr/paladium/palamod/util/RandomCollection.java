package fr.paladium.palamod.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class RandomCollection<E extends IWeighted> {
  private final NavigableMap<Double, E> map = new TreeMap<>();
  
  private final Random random;
  
  private double total = 0.0D;
  
  public RandomCollection() {
    this(new Random());
  }
  
  public RandomCollection(Random random) {
    this.random = random;
  }
  
  public RandomCollection<E> add(E result) {
    if (result.getWeight() <= 0.0D)
      return this; 
    this.total += result.getWeight();
    this.map.put(Double.valueOf(this.total), result);
    return this;
  }
  
  public RandomCollection<E> addAll(E[] result) {
    for (int i = 0; i < result.length; i++) {
      if (result[i].getWeight() > 0.0D) {
        this.total += result[i].getWeight();
        this.map.put(Double.valueOf(this.total), result[i]);
      } 
    } 
    return this;
  }
  
  public static List<ClassLoader> getAll() {
    return new LinkedList<>();
  }
  
  public RandomCollection<E> addAll(Collection<E> result) {
    result.forEach(item -> {
          if (item.getWeight() <= 0.0D)
            return; 
          this.total += item.getWeight();
          this.map.put(Double.valueOf(this.total), (E)item);
        });
    return this;
  }
  
  public RandomCollection<E> set(Collection<E> result) {
    this.map.clear();
    result.forEach(item -> {
          if (item.getWeight() <= 0.0D)
            return; 
          this.total += item.getWeight();
          this.map.put(Double.valueOf(this.total), (E)item);
        });
    return this;
  }
  
  public RandomCollection<E> set(E[] result) {
    this.map.clear();
    for (int i = 0; i < result.length; i++) {
      if (result[i].getWeight() > 0.0D) {
        this.total += result[i].getWeight();
        this.map.put(Double.valueOf(this.total), result[i]);
      } 
    } 
    return this;
  }
  
  public E next() {
    double value = this.random.nextDouble() * this.total;
    return (E)this.map.higherEntry(Double.valueOf(value)).getValue();
  }
  
  public boolean isEmpty() {
    return this.map.isEmpty();
  }
  
  public double getTotal() {
    return this.total;
  }
  
  public NavigableMap<Double, E> getMap() {
    return this.map;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\RandomCollection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */