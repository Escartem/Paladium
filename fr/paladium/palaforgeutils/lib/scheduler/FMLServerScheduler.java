package fr.paladium.palaforgeutils.lib.scheduler;

import com.google.common.collect.Queues;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class FMLServerScheduler {
  private static FMLServerScheduler instance;
  
  private final Queue<Runnable> scheduledTasks = Queues.newConcurrentLinkedQueue();
  
  public FMLServerScheduler() {
    instance = this;
  }
  
  @SubscribeEvent
  public void onTick(TickEvent.ServerTickEvent e) {
    if (e.phase != TickEvent.Phase.END || this.scheduledTasks.isEmpty())
      return; 
    List<Runnable> toRemove = new ArrayList<>();
    this.scheduledTasks.forEach(toRemove::add);
    toRemove.forEach(Runnable::run);
    this.scheduledTasks.removeAll(toRemove);
  }
  
  public FMLServerScheduler add(Runnable... runnables) {
    if (runnables.length == 0)
      return this; 
    if (runnables.length == 1) {
      this.scheduledTasks.add(runnables[0]);
      return this;
    } 
    this.scheduledTasks.addAll(Arrays.asList(runnables));
    return this;
  }
  
  public static FMLServerScheduler getInstance() {
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\scheduler\FMLServerScheduler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */