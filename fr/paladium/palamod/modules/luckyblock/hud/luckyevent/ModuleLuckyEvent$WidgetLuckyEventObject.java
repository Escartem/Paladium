package fr.paladium.palamod.modules.luckyblock.hud.luckyevent;

import fr.paladium.palamod.modules.luckyblock.utils.LuckyEvents;

public class WidgetLuckyEventObject {
  private LuckyEvents event;
  
  private long duration;
  
  private long startTime;
  
  private int counter;
  
  public WidgetLuckyEventObject(LuckyEvents event, long duration, long startTime, int counter) {
    this.event = event;
    this.duration = duration;
    this.startTime = startTime;
    this.counter = counter;
  }
  
  public LuckyEvents getEvent() {
    return this.event;
  }
  
  public long getDuration() {
    return this.duration;
  }
  
  public long getStartTime() {
    return this.startTime;
  }
  
  public int getCounter() {
    return this.counter;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\hud\luckyevent\ModuleLuckyEvent$WidgetLuckyEventObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */