package fr.paladium.palaschematicmod.server.async;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PalaSchematicQueue {
  private final Map<Runnable, Runnable> tasks = new ConcurrentHashMap<>();
  
  public void addTask(Runnable task) {
    this.tasks.put(task, null);
  }
  
  public void addTask(Runnable task, Runnable callback) {
    this.tasks.put(task, callback);
  }
  
  public void executeNext() {
    Runnable task = this.tasks.keySet().stream().findFirst().orElse(null);
    if (task != null) {
      Runnable callback = this.tasks.remove(task);
      task.run();
      if (callback != null)
        callback.run(); 
    } 
  }
  
  public boolean hasTasks() {
    return !this.tasks.isEmpty();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaschematicmod\server\async\PalaSchematicQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */