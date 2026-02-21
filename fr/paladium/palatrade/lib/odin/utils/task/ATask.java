package fr.paladium.palatrade.lib.odin.utils.task;

import fr.paladium.palatrade.lib.odin.modules.IProxy;

public abstract class ATask {
  private String name;
  
  private boolean running;
  
  private Schedule info;
  
  private long eachInfo;
  
  private long delayInfo;
  
  private Thread thread;
  
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
    this.eachInfo = DurationConverter.fromStringToMillis(this.info.every());
    this.delayInfo = DurationConverter.fromStringToMillis(this.info.delay());
    this.thread = new Thread(() -> thread(), this.name);
    this.thread.start();
    return true;
  }
  
  private void thread() {
    try {
      int iter = 0;
      Thread.sleep(this.delayInfo);
      while (true) {
        if (this.info.async()) {
          run();
        } else {
          synchronized (IProxy.class) {
            run();
          } 
        } 
        if (this.info.repeat() > 0 && iter++ >= this.info.repeat())
          break; 
        Thread.sleep(this.eachInfo);
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
    this.running = false;
  }
  
  public boolean isRunning() {
    return this.running;
  }
  
  public String getName() {
    return this.name;
  }
  
  public Schedule getInfo() {
    return this.info;
  }
  
  public abstract void run();
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\lib\odi\\utils\task\ATask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */