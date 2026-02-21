package fr.paladium.palaforgeutils.lib.monitor.object.primitive;

import fr.paladium.palaforgeutils.lib.monitor.Monitor;

public class FloatMonitor extends Monitor<Float> {
  public FloatMonitor() {
    super(Float.valueOf(0.0F));
  }
  
  public FloatMonitor(float value) {
    super(Float.valueOf(value));
  }
  
  public void add(float value) {
    float updatedValue = ((Float)get()).floatValue() + value;
    set(Float.valueOf(updatedValue));
    publish();
  }
  
  public void subtract(float value) {
    float updatedValue = ((Float)get()).floatValue() - value;
    set(Float.valueOf(updatedValue));
    publish();
  }
  
  public void multiply(float value) {
    float updatedValue = ((Float)get()).floatValue() * value;
    set(Float.valueOf(updatedValue));
    publish();
  }
  
  public void divide(float value) {
    if (value != 0.0F) {
      float updatedValue = ((Float)get()).floatValue() / value;
      set(Float.valueOf(updatedValue));
      publish();
    } else {
      throw new ArithmeticException("Division by zero");
    } 
  }
  
  public void increment() {
    float updatedValue = ((Float)get()).floatValue() + 1.0F;
    set(Float.valueOf(updatedValue));
    publish();
  }
  
  public void decrement() {
    float updatedValue = ((Float)get()).floatValue() - 1.0F;
    set(Float.valueOf(updatedValue));
    publish();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\monitor\object\primitive\FloatMonitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */