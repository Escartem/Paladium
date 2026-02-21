package fr.paladium.palaforgeutils.lib.rabbitmq.network.internal;

import com.google.common.collect.Queues;
import java.util.Queue;
import lombok.NonNull;

public class InternalRabbitThread extends Thread {
  private final Queue<Runnable> queue = Queues.newLinkedBlockingDeque();
  
  public InternalRabbitThread(int id) {
    super("PalaForgeUtils-InternalRabbitThread/" + id);
    start();
  }
  
  public void run() {
    while (true) {
      while (!this.queue.isEmpty())
        ((Runnable)this.queue.poll()).run(); 
      synchronized (this) {
        try {
          wait();
        } catch (Exception ex) {
          ex.printStackTrace();
        } 
      } 
    } 
  }
  
  public void sendUpsignal() {
    synchronized (this) {
      notify();
    } 
  }
  
  public boolean isAvailable() {
    return (getState() == Thread.State.WAITING);
  }
  
  public int getQueueSize() {
    return this.queue.size();
  }
  
  public void addQueuedPacket(@NonNull Runnable action) {
    if (action == null)
      throw new NullPointerException("action is marked non-null but is null"); 
    this.queue.add(action);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\rabbitmq\network\internal\InternalRabbitThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */