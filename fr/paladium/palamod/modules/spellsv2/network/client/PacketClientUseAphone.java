package fr.paladium.palamod.modules.spellsv2.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.spellsv2.utils.ClientManager;
import io.netty.buffer.ByteBuf;

public class PacketClientUseAphone implements IMessage {
  public double x;
  
  public double y;
  
  public double z;
  
  public int tier;
  
  public boolean active;
  
  public PacketClientUseAphone() {}
  
  public PacketClientUseAphone(double x, double y, double z, int tier, boolean active) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.tier = tier;
    this.active = active;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.x = buf.readDouble();
    this.y = buf.readDouble();
    this.z = buf.readDouble();
    this.tier = buf.readInt();
    this.active = buf.readBoolean();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeDouble(this.x);
    buf.writeDouble(this.y);
    buf.writeDouble(this.z);
    buf.writeInt(this.tier);
    buf.writeBoolean(this.active);
  }
  
  public static class Handler implements IMessageHandler<PacketClientUseAphone, IMessage> {
    public IMessage onMessage(PacketClientUseAphone message, MessageContext ctx) {
      if (message.active) {
        ClientManager.addMuted(message.x, message.y, message.z, message.tier);
      } else {
        ClientManager.removeMuted(message.x, message.y, message.z, message.tier);
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\client\PacketClientUseAphone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */