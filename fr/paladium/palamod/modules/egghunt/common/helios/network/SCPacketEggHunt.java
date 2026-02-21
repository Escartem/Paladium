package fr.paladium.palamod.modules.egghunt.common.helios.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.egghunt.common.helios.ModuleEggHunt;
import io.netty.buffer.ByteBuf;

public class SCPacketEggHunt implements IMessage {
  private boolean active;
  
  private boolean canXp;
  
  public SCPacketEggHunt() {}
  
  public SCPacketEggHunt(boolean active, boolean canXp) {
    this.active = active;
    this.canXp = canXp;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.active = buf.readBoolean();
    this.canXp = buf.readBoolean();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeBoolean(this.active);
    buf.writeBoolean(this.canXp);
  }
  
  public static class Handler implements IMessageHandler<SCPacketEggHunt, IMessage> {
    public IMessage onMessage(SCPacketEggHunt message, MessageContext ctx) {
      ModuleEggHunt.getInstance().setActive(message.active);
      ModuleEggHunt.getInstance().setCanXp(message.canXp);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\common\helios\network\SCPacketEggHunt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */