package fr.paladium.common.combat.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.common.CommonModule;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;

public class SCPacketCombatTag implements IMessage {
  private long now;
  
  private long end;
  
  private List<String> opponents;
  
  public SCPacketCombatTag() {}
  
  public SCPacketCombatTag(long now, long cooldown, List<String> opponents) {
    this.now = now;
    this.end = cooldown;
    this.opponents = opponents;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.now = buf.readLong();
    this.end = buf.readLong();
    this.opponents = new ArrayList<>();
    int size = buf.readInt();
    for (int i = 0; i < size; i++)
      this.opponents.add(ByteBufUtils.readUTF8String(buf)); 
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeLong(this.now);
    buf.writeLong(this.end);
    buf.writeInt(this.opponents.size());
    for (String opponent : this.opponents)
      ByteBufUtils.writeUTF8String(buf, opponent); 
  }
  
  public static class Handler implements IMessageHandler<SCPacketCombatTag, IMessage> {
    public IMessage onMessage(SCPacketCombatTag message, MessageContext ctx) {
      CommonModule.getInstance().getCombatTag().setTimeOffset(System.currentTimeMillis() - message.now);
      CommonModule.getInstance().getCombatTag().setEnd(message.end);
      CommonModule.getInstance().getCombatTag().setOpponents(message.opponents);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\common\combat\network\SCPacketCombatTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */