package fr.paladium.palaforgeutils.lib.monitor.object.primitive;

import fr.paladium.palaforgeutils.lib.monitor.Monitor;

public class DoubleMonitor extends Monitor<Double> {
  public DoubleMonitor() {
    super(Double.valueOf(0.0D));
  }
  
  public DoubleMonitor(double value) {
    super(Double.valueOf(value));
  }
  
  public void add(double value) {
    double updatedValue = ((Double)get()).doubleValue() + value;
    set(Double.valueOf(updatedValue));
    publish();
  }
  
  public void subtract(double value) {
    double updatedValue = ((Double)get()).doubleValue() - value;
    set(Double.valueOf(updatedValue));
    publish();
  }
  
  public void multiply(double value) {
    double updatedValue = ((Double)get()).doubleValue() * value;
    set(Double.valueOf(updatedValue));
    publish();
  }
  
  public void divide(double value) {
    if (value != 0.0D) {
      double updatedValue = ((Double)get()).doubleValue() / value;
      set(Double.valueOf(updatedValue));
      publish();
    } else {
      throw new ArithmeticException("Division by zero");
    } 
  }
  
  public void increment() {
    double updatedValue = ((Double)get()).doubleValue() + 1.0D;
    set(Double.valueOf(updatedValue));
    publish();
  }
  
  public void decrement() {
    double updatedValue = ((Double)get()).doubleValue() - 1.0D;
    set(Double.valueOf(updatedValue));
    publish();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\monitor\object\primitive\DoubleMonitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */