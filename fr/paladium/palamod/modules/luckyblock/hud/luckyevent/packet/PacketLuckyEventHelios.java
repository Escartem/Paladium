package fr.paladium.palamod.modules.luckyblock.hud.luckyevent.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.ModuleLuckyEvent;
import io.netty.buffer.ByteBuf;

public class PacketLuckyEventHelios implements IMessage {
  private boolean enable;
  
  private String eventClassName;
  
  private long duration;
  
  private long startTime;
  
  private int counter;
  
  public boolean isEnable() {
    return this.enable;
  }
  
  public String getEventClassName() {
    return this.eventClassName;
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
  
  public PacketLuckyEventHelios() {
    this.enable = false;
  }
  
  public PacketLuckyEventHelios(boolean enable, String eventClassName) {
    this.enable = enable;
    this.eventClassName = eventClassName;
    this.counter = 0;
    this.duration = 0L;
    this.startTime = 0L;
  }
  
  public PacketLuckyEventHelios(String eventClassName, long duration, long startTime) {
    this.enable = true;
    this.eventClassName = eventClassName;
    this.duration = duration;
    this.startTime = startTime;
    this.counter = 0;
  }
  
  public PacketLuckyEventHelios(String eventClassName, int counter) {
    this.enable = true;
    this.eventClassName = eventClassName;
    this.counter = counter;
    this.duration = 0L;
    this.startTime = 0L;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.enable = buf.readBoolean();
    if (this.enable) {
      this.eventClassName = ByteBufUtils.readUTF8String(buf);
      this.duration = buf.readLong();
      this.startTime = buf.readLong();
      this.counter = buf.readInt();
    } 
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeBoolean(this.enable);
    if (this.enable) {
      ByteBufUtils.writeUTF8String(buf, this.eventClassName);
      buf.writeLong(this.duration);
      buf.writeLong(this.startTime);
      buf.writeInt(this.counter);
    } 
  }
  
  public static class Handler implements IMessageHandler<PacketLuckyEventHelios, IMessage> {
    public IMessage onMessage(PacketLuckyEventHelios message, MessageContext ctx) {
      ModuleLuckyEvent.getInstance().updateState(message);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\hud\luckyevent\packet\PacketLuckyEventHelios.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */