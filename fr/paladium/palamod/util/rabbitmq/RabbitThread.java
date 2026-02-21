package fr.paladium.palamod.util.rabbitmq;

import com.google.common.collect.Queues;
import java.util.Queue;

public class RabbitThread extends Thread {
  private final Queue<RabbitPacket> queue = Queues.newLinkedBlockingDeque();
  
  private final RabbitService service;
  
  public RabbitThread(RabbitService service, int id) {
    super("SyncServerRabbit/" + id);
    this.service = service;
    start();
  }
  
  public void run() {
    while (true) {
      while (!this.queue.isEmpty()) {
        RabbitPacket packet = this.queue.poll();
        this.service.sendBlockingPacket(packet);
      } 
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
  
  public void addQueuedPacket(RabbitPacket packet) {
    this.queue.add(packet);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\rabbitmq\RabbitThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */