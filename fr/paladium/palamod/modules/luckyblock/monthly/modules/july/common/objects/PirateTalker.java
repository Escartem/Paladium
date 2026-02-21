package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.objects;

import fr.paladium.common.CommonModule;

public class PirateTalker {
  private int messageSent;
  
  private long expirationMillis;
  
  public PirateTalker(int messageSent, long expirationMillis) {
    this.messageSent = messageSent;
    this.expirationMillis = expirationMillis;
  }
  
  public int getMessageSent() {
    return this.messageSent;
  }
  
  public long getExpirationMillis() {
    return this.expirationMillis;
  }
  
  public PirateTalker(long time) {
    this.messageSent = 0;
    this.expirationMillis = System.currentTimeMillis() + time;
    CommonModule.getInstance().getConfig().getServerName();
  }
  
  public void increment() {
    this.messageSent++;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\objects\PirateTalker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */