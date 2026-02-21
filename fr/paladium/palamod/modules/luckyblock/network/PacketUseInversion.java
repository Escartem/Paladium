package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.spellsv2.ClientProxy;
import io.netty.buffer.ByteBuf;

public class PacketUseInversion implements IMessage {
  public boolean inversion;
  
  public PacketUseInversion() {}
  
  public PacketUseInversion(boolean active) {
    this.inversion = active;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.inversion = buf.readBoolean();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeBoolean(this.inversion);
  }
  
  public static class Handler implements IMessageHandler<PacketUseInversion, IMessage> {
    public IMessage onMessage(PacketUseInversion message, MessageContext ctx) {
      ClientProxy.customMovementInput.confused = message.inversion;
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketUseInversion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */