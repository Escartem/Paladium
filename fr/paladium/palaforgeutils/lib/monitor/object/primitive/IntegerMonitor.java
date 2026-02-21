package fr.paladium.palaforgeutils.lib.monitor.object.primitive;

import fr.paladium.palaforgeutils.lib.monitor.Monitor;

public class IntegerMonitor extends Monitor<Integer> {
  public IntegerMonitor() {
    super(Integer.valueOf(0));
  }
  
  public IntegerMonitor(int value) {
    super(Integer.valueOf(value));
  }
  
  public void increment() {
    int updatedValue = ((Integer)get()).intValue() + 1;
    set(Integer.valueOf(updatedValue));
    publish();
  }
  
  public void decrement() {
    int updatedValue = ((Integer)get()).intValue() - 1;
    set(Integer.valueOf(updatedValue));
    publish();
  }
  
  public void add(int value) {
    int updatedValue = ((Integer)get()).intValue() + value;
    set(Integer.valueOf(updatedValue));
    publish();
  }
  
  public void subtract(int value) {
    int updatedValue = ((Integer)get()).intValue() - value;
    set(Integer.valueOf(updatedValue));
    publish();
  }
  
  public void multiply(int value) {
    int updatedValue = ((Integer)get()).intValue() * value;
    set(Integer.valueOf(updatedValue));
    publish();
  }
  
  public void divide(int value) {
    if (value != 0) {
      int updatedValue = ((Integer)get()).intValue() / value;
      set(Integer.valueOf(updatedValue));
      publish();
    } else {
      throw new ArithmeticException("Division by zero");
    } 
  }
  
  public void power(int exponent) {
    int updatedValue = (int)Math.pow(((Integer)get()).intValue(), exponent);
    set(Integer.valueOf(updatedValue));
    publish();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\monitor\object\primitive\IntegerMonitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */