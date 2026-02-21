package fr.paladium.palamod.scheduler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;

public abstract class PaladiumRunnable implements Runnable {
  private int taskId = -1;
  
  public int getTaskId() throws IllegalStateException {
    int id = this.taskId;
    if (id == -1)
      throw new IllegalStateException("Not scheduled yet"); 
    return id;
  }
  
  public PaladiumTask runTaskLater(long delay) {
    checkState();
    return setupId(PaladiumScheduler.INSTANCE.runTaskLater(this, delay));
  }
  
  public PaladiumTask runTaskTimer(long delay, long period) throws IllegalArgumentException, IllegalStateException {
    checkState();
    return setupId(PaladiumScheduler.INSTANCE.runTaskTimer(this, delay, period));
  }
  
  public void cancel() throws IllegalStateException {
    PaladiumScheduler.INSTANCE.cancelTask(getTaskId());
  }
  
  private void checkState() {
    if (this.taskId != -1)
      throw new IllegalStateException("Already scheduled as " + this.taskId); 
  }
  
  public static GameSettings g() {
    return (Minecraft.func_71410_x()).field_71474_y;
  }
  
  private PaladiumTask setupId(PaladiumTask task) {
    this.taskId = task.getTaskId();
    return task;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\scheduler\PaladiumRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */