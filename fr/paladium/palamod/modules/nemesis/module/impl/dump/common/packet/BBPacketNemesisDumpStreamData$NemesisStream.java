package fr.paladium.palamod.modules.nemesis.module.impl.dump.common.packet;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NemesisStream {
  private final transient Queue<BBPacketNemesisDumpStreamData.NemesisStreamObject> objects;
  
  private final int toSend;
  
  private boolean sending;
  
  private int sended;
  
  public void setSending(boolean sending) {
    this.sending = sending;
  }
  
  public void setSended(int sended) {
    this.sended = sended;
  }
  
  public Queue<BBPacketNemesisDumpStreamData.NemesisStreamObject> getObjects() {
    return this.objects;
  }
  
  public int getToSend() {
    return this.toSend;
  }
  
  public boolean isSending() {
    return this.sending;
  }
  
  public int getSended() {
    return this.sended;
  }
  
  public NemesisStream(List<BBPacketNemesisDumpStreamData.NemesisStreamObject> objects) {
    this.objects = new LinkedList<>(objects);
    this.toSend = objects.size();
    this.sending = false;
    this.sended = 0;
  }
  
  public float getProgression() {
    if (this.toSend == 0)
      return 1.0F; 
    return this.sended / this.toSend;
  }
  
  public boolean isEmpty() {
    return this.objects.isEmpty();
  }
  
  public BBPacketNemesisDumpStreamData.NemesisStreamObject send() {
    if (this.objects.isEmpty()) {
      this.sending = false;
      return null;
    } 
    BBPacketNemesisDumpStreamData.NemesisStreamObject object = this.objects.poll();
    if (++this.sended >= this.toSend) {
      this.sending = false;
    } else {
      this.sending = true;
    } 
    return object;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\nemesis\module\impl\dump\common\packet\BBPacketNemesisDumpStreamData$NemesisStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */