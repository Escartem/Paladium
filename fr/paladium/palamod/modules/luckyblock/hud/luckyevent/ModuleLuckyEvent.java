package fr.paladium.palamod.modules.luckyblock.hud.luckyevent;

import cpw.mods.fml.relauncher.Side;
import fr.paladium.helios.module.BaseModule;
import fr.paladium.helios.module.utils.annotations.Module;
import fr.paladium.helios.module.utils.interfaces.IWidget;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.packet.PacketLuckyEventHelios;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyEvents;
import java.util.TreeMap;

@Module(name = "luckyevent", version = 1)
public class ModuleLuckyEvent extends BaseModule<WidgetLuckyEvent> {
  private static ModuleLuckyEvent instance;
  
  private TreeMap<String, WidgetLuckyEventObject> eventMap;
  
  public TreeMap<String, WidgetLuckyEventObject> getEventMap() {
    return this.eventMap;
  }
  
  private int currentEventIndex = 0;
  
  public int getCurrentEventIndex() {
    return this.currentEventIndex;
  }
  
  public void setCurrentEventIndex(int currentEventIndex) {
    this.currentEventIndex = currentEventIndex;
  }
  
  private int tickCpt = 0;
  
  public int getTickCpt() {
    return this.tickCpt;
  }
  
  public void setTickCpt(int tickCpt) {
    this.tickCpt = tickCpt;
  }
  
  public ModuleLuckyEvent() {
    super((IWidget)new WidgetLuckyEvent());
    instance = this;
    this.eventMap = new TreeMap<>();
  }
  
  public void init(Side side) {
    setEnable(true);
    registerPacket(PacketLuckyEventHelios.Handler.class, PacketLuckyEventHelios.class, Side.CLIENT);
    super.init(side);
  }
  
  public static ModuleLuckyEvent getInstance() {
    return instance;
  }
  
  private LuckyEvents getEvent(String eventClassName) {
    try {
      Class<?> clazz = Class.forName(eventClassName);
      return LuckyEvents.fromClass(clazz);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public void updateState(PacketLuckyEventHelios message) {
    if (message.isEnable()) {
      setEnable(true);
      LuckyEvents event = getEvent(message.getEventClassName());
      if (event != null)
        this.eventMap.put(message.getEventClassName(), new WidgetLuckyEventObject(event, message.getDuration(), message.getStartTime(), message.getCounter())); 
    } else if (message.getEventClassName() != null) {
      this.eventMap.remove(message.getEventClassName());
    } else {
      disable();
    } 
  }
  
  private void disable() {
    this.eventMap.clear();
  }
  
  public static class WidgetLuckyEventObject {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\hud\luckyevent\ModuleLuckyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */