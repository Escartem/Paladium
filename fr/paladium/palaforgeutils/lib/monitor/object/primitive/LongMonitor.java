package fr.paladium.palaforgeutils.lib.monitor.object.primitive;

import fr.paladium.palaforgeutils.lib.monitor.Monitor;

public class LongMonitor extends Monitor<Long> {
  public LongMonitor() {
    super(Long.valueOf(0L));
  }
  
  public LongMonitor(long value) {
    super(Long.valueOf(value));
  }
  
  public void increment() {
    long updatedValue = ((Long)get()).longValue() + 1L;
    set(Long.valueOf(updatedValue));
    publish();
  }
  
  public void decrement() {
    long updatedValue = ((Long)get()).longValue() - 1L;
    set(Long.valueOf(updatedValue));
    publish();
  }
  
  public void add(long value) {
    long updatedValue = ((Long)get()).longValue() + value;
    set(Long.valueOf(updatedValue));
    publish();
  }
  
  public void subtract(long value) {
    long updatedValue = ((Long)get()).longValue() - value;
    set(Long.valueOf(updatedValue));
    publish();
  }
  
  public void multiply(long value) {
    long updatedValue = ((Long)get()).longValue() * value;
    set(Long.valueOf(updatedValue));
    publish();
  }
  
  public void divide(long value) {
    if (value != 0L) {
      long updatedValue = ((Long)get()).longValue() / value;
      set(Long.valueOf(updatedValue));
      publish();
    } else {
      throw new ArithmeticException("Division by zero");
    } 
  }
  
  public void power(int exponent) {
    long updatedValue = (long)Math.pow(((Long)get()).longValue(), exponent);
    set(Long.valueOf(updatedValue));
    publish();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\monitor\object\primitive\LongMonitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */