package fr.paladium.palamod.modules.spellsv2.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.spellsv2.utils.ClientManager;
import io.netty.buffer.ByteBuf;

public class PacketClientUpdateClientManager implements IMessage {
  public double points;
  
  public int level;
  
  public PacketClientUpdateClientManager() {}
  
  public PacketClientUpdateClientManager(double points, int level) {
    this.points = points;
    this.level = level;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.points = buf.readDouble();
    this.level = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeDouble(this.points);
    buf.writeInt(this.level);
  }
  
  public static class Handler implements IMessageHandler<PacketClientUpdateClientManager, IMessage> {
    public IMessage onMessage(PacketClientUpdateClientManager message, MessageContext ctx) {
      if (message.points >= 0.0D)
        ClientManager.setPoints(message.points); 
      if (message.level >= 0)
        ClientManager.setLevel(message.level); 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\client\PacketClientUpdateClientManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */