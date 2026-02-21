package fr.paladium.palaforgeutils.lib.task;

import fr.paladium.palaforgeutils.lib.proxy.IModProxy;

public abstract class ATask {
  public static final long DISABLE_DELAY = -1L;
  
  private final String name;
  
  private Schedule info;
  
  private int repeatInfo;
  
  private long eachInfo;
  
  private long delayInfo;
  
  private boolean running;
  
  private boolean stopped;
  
  private Thread thread;
  
  public String getName() {
    return this.name;
  }
  
  public Schedule getInfo() {
    return this.info;
  }
  
  public int getRepeatInfo() {
    return this.repeatInfo;
  }
  
  public long getEachInfo() {
    return this.eachInfo;
  }
  
  public long getDelayInfo() {
    return this.delayInfo;
  }
  
  public boolean isRunning() {
    return this.running;
  }
  
  public boolean isStopped() {
    return this.stopped;
  }
  
  public Thread getThread() {
    return this.thread;
  }
  
  public ATask(String name) {
    this.name = name;
    this.running = false;
  }
  
  public boolean start() {
    if (this.running) {
      System.err.println("Task " + this.name + " is already started.");
      return false;
    } 
    this.running = true;
    this.info = getClass().<Schedule>getAnnotation(Schedule.class);
    if (this.info == null) {
      System.err.println("Task " + this.name + " doesn't have valid @schedule.");
      return false;
    } 
    if (this.eachInfo == 0L) {
      this.eachInfo = DurationConverter.fromStringToMillis(this.info.every());
      long everyMillis = this.info.everyMillis();
      if (everyMillis != -1L)
        this.eachInfo = everyMillis; 
    } 
    if (this.delayInfo == 0L) {
      this.delayInfo = DurationConverter.fromStringToMillis(this.info.delay());
      long delayMillis = this.info.delayMillis();
      if (delayMillis != -1L)
        this.delayInfo = delayMillis; 
    } 
    if (this.repeatInfo == 0)
      this.repeatInfo = this.info.repeat(); 
    this.thread = new Thread(() -> thread(), this.name);
    this.thread.start();
    return true;
  }
  
  private void thread() {
    try {
      int iter = 1;
      Thread.sleep(this.delayInfo);
      while (this.repeatInfo != 0 && !this.stopped) {
        if (this.info.async()) {
          run();
        } else {
          synchronized (IModProxy.class) {
            run();
          } 
        } 
        if (this.repeatInfo > 0 && ++iter > this.repeatInfo)
          break; 
        Thread.sleep(getRepeatTime(iter));
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
    this.running = false;
  }
  
  public long getRepeatTime(int iter) {
    return this.eachInfo;
  }
  
  public ATask setDelayInfo(long delayInfo) {
    this.delayInfo = delayInfo;
    return this;
  }
  
  public ATask setEachInfo(long eachInfo) {
    this.eachInfo = eachInfo;
    return this;
  }
  
  public ATask setRepeatInfo(int repeatInfo) {
    this.repeatInfo = repeatInfo;
    return this;
  }
  
  public void stop() {
    this.stopped = true;
  }
  
  public abstract void run();
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\task\ATask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */