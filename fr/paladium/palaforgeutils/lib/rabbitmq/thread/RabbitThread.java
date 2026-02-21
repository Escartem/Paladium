package fr.paladium.palaforgeutils.lib.rabbitmq.thread;

import com.google.common.collect.Queues;
import fr.paladium.palaforgeutils.lib.rabbitmq.packet.RabbitPacket;
import fr.paladium.palaforgeutils.lib.rabbitmq.service.RabbitService;
import java.util.Queue;

public class RabbitThread extends Thread {
  private final Queue<RabbitPacket> queue = Queues.newLinkedBlockingDeque();
  
  private final RabbitService service;
  
  public RabbitThread(RabbitService service, int id) {
    super("PForgeRabbit/" + id);
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


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\rabbitmq\thread\RabbitThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */