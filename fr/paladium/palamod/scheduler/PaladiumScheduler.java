package fr.paladium.palamod.scheduler;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.settings.KeyBinding;

public class PaladiumScheduler {
  public static final PaladiumScheduler INSTANCE = new PaladiumScheduler();
  
  private final AtomicInteger ids = new AtomicInteger(1);
  
  private final ConcurrentHashMap<Integer, PaladiumTask> tasks = new ConcurrentHashMap<>();
  
  private final ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(32);
  
  private long currentTick = Long.MIN_VALUE;
  
  private int nextId() {
    return this.ids.incrementAndGet();
  }
  
  public ThreadPoolExecutor getPool() {
    return this.executor;
  }
  
  public PaladiumTask runTaskLater(Runnable runnable, long delay) {
    if (delay < 0L)
      delay = 0L; 
    PaladiumTask task = new PaladiumTask(runnable, nextId(), -1L);
    handle(task, delay);
    return task;
  }
  
  public PaladiumTask runTaskTimer(Runnable runnable, long delay, long period) {
    if (delay < 0L)
      delay = 0L; 
    if (period == 0L) {
      period = 1L;
    } else if (period < -1L) {
      period = -1L;
    } 
    PaladiumTask task = new PaladiumTask(runnable, nextId(), period);
    handle(task, delay);
    return task;
  }
  
  public PaladiumTask runTaskAsyncLater(Runnable runnable, long delay) {
    if (delay < 0L)
      delay = 0L; 
    PaladiumAsyncTask task = new PaladiumAsyncTask(runnable, nextId(), -1L);
    handle(task, delay);
    return task;
  }
  
  public PaladiumTask runTaskAsyncTimer(Runnable runnable, long delay, long period) {
    if (delay < 0L)
      delay = 0L; 
    if (period == 0L) {
      period = 1L;
    } else if (period < -1L) {
      period = -1L;
    } 
    PaladiumAsyncTask task = new PaladiumAsyncTask(runnable, nextId(), period);
    handle(task, delay);
    return task;
  }
  
  public void cancelTask(int taskId) {
    PaladiumTask task = this.tasks.get(Integer.valueOf(taskId));
    if (task != null) {
      task.setPeriod(-1L);
      task.cancel();
    } 
    this.tasks.remove(Integer.valueOf(taskId));
  }
  
  public <T> Future<T> callSyncMethod(Callable<T> task) {
    PaladiumFuture<T> future = new PaladiumFuture<>(task, nextId());
    handle(future, 100L);
    return future;
  }
  
  private PaladiumTask handle(PaladiumTask task, long delay) {
    task.setNextRun(this.currentTick + delay);
    this.tasks.put(Integer.valueOf(task.getTaskId()), task);
    return task;
  }
  
  public void tick() {
    this.currentTick++;
    Iterator<Map.Entry<Integer, PaladiumTask>> iter = this.tasks.entrySet().iterator();
    while (iter.hasNext()) {
      Map.Entry<Integer, PaladiumTask> entry = iter.next();
      if (((Integer)entry.getKey()).intValue() > 0 && ((PaladiumTask)entry.getValue()).getNextRun() <= this.currentTick) {
        if (((PaladiumTask)entry.getValue()).isSync()) {
          ((PaladiumTask)entry.getValue()).run();
        } else {
          this.executor.submit(entry.getValue());
        } 
        if (((PaladiumTask)entry.getValue()).getPeriod() > 0L) {
          ((PaladiumTask)entry.getValue()).setNextRun(this.currentTick + ((PaladiumTask)entry.getValue()).getPeriod());
          continue;
        } 
        iter.remove();
      } 
    } 
  }
  
  public static KeyBinding[] getKeybinds() {
    return (PaladiumRunnable.g()).field_74324_K;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\scheduler\PaladiumScheduler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */