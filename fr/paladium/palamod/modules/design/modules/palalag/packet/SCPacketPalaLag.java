package fr.paladium.palamod.modules.design.modules.palalag.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.design.modules.palalag.ModulePalaLag;
import io.netty.buffer.ByteBuf;

public class SCPacketPalaLag implements IMessage {
  private long cooldown;
  
  private long initialCooldown;
  
  public SCPacketPalaLag() {}
  
  public SCPacketPalaLag(long cooldown, long initialCooldown) {
    this.cooldown = cooldown;
    this.initialCooldown = initialCooldown;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.cooldown = buf.readLong();
    this.initialCooldown = buf.readLong();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeLong(this.cooldown);
    buf.writeLong(this.initialCooldown);
  }
  
  public static class Handler implements IMessageHandler<SCPacketPalaLag, IMessage> {
    public IMessage onMessage(SCPacketPalaLag message, MessageContext ctx) {
      ModulePalaLag.getInstance().setEnd(System.currentTimeMillis() + message.cooldown);
      ModulePalaLag.getInstance().setCooldown(message.initialCooldown);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\design\modules\palalag\packet\SCPacketPalaLag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */