package fr.paladium.palamod.modules.spellsv2.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.spellsv2.ClientProxy;
import io.netty.buffer.ByteBuf;

public class PacketClientFreeze implements IMessage {
  private boolean freeze;
  
  public PacketClientFreeze() {}
  
  public PacketClientFreeze(boolean freeze) {
    this.freeze = freeze;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.freeze = buf.readBoolean();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeBoolean(this.freeze);
  }
  
  public static class Handler implements IMessageHandler<PacketClientFreeze, IMessage> {
    public IMessage onMessage(PacketClientFreeze message, MessageContext ctx) {
      if (ClientProxy.customMovementInput != null)
        ClientProxy.customMovementInput.freeze = message.freeze; 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\client\PacketClientFreeze.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */