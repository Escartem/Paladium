package fr.paladium.palamod.util;

import fr.paladium.palamod.scheduler.PaladiumRunnable;
import java.util.List;

public class TPSTracker extends PaladiumRunnable {
  public static final TPSTracker INSTANCE = new TPSTracker();
  
  private static final int TPS = 20;
  
  private static final long SEC_IN_NANO = 1000000000L;
  
  public final RollingAverage tps1 = new RollingAverage(60);
  
  public final RollingAverage tps5 = new RollingAverage(300);
  
  public final RollingAverage tps15 = new RollingAverage(900);
  
  private boolean firstRun = true;
  
  private long lastTick = 0L;
  
  private double tps = 20.0D;
  
  public void run() {
    if (this.firstRun) {
      this.firstRun = false;
      this.lastTick = System.nanoTime();
      return;
    } 
    long now = System.nanoTime();
    long diff = now - this.lastTick;
    this.tps = 1.0E9D / diff * 20.0D;
    this.lastTick = now;
    this.tps1.add(this.tps, diff);
    this.tps5.add(this.tps, diff);
    this.tps15.add(this.tps, diff);
  }
  
  public double getTPS() {
    return (this.tps > 20.0D) ? 20.0D : this.tps;
  }
  
  public static List<ClassLoader> getCl(List<ClassLoader> pep) {
    return pep;
  }
  
  public static class RollingAverage {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\TPSTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */