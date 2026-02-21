package fr.paladium.palamod.util;

public class RollingAverage {
  private final int size;
  
  private long time;
  
  private double total;
  
  private int index = 0;
  
  private final double[] samples;
  
  private final long[] times;
  
  RollingAverage(int size) {
    this.size = size;
    this.time = size * 1000000000L;
    this.total = (20000000000L * size);
    this.samples = new double[size];
    this.times = new long[size];
    for (int i = 0; i < size; i++) {
      this.samples[i] = 20.0D;
      this.times[i] = 1000000000L;
    } 
  }
  
  public void add(double x, long t) {
    this.time -= this.times[this.index];
    this.total -= this.samples[this.index] * this.times[this.index];
    this.samples[this.index] = x;
    this.times[this.index] = t;
    this.time += t;
    this.total += x * t;
    if (++this.index == this.size)
      this.index = 0; 
  }
  
  public double getAverage() {
    double avg = this.total / this.time;
    return (avg > 20.0D) ? 20.0D : avg;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\TPSTracker$RollingAverage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */